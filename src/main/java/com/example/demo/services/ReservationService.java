package com.example.demo.services;

import com.example.demo.models.Invoice;
import com.example.demo.models.Reservation;
import com.example.demo.repositories.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

//Lavet af Magnus og Joakim
@Service
public class ReservationService {

    @Autowired
    ReservationRepo reservationRepo;

    @Autowired
    AccessoryService accessoryService;

    @Autowired
    MotorhomeService motorhomeService;

    @Autowired
    InvoiceService invoiceService;

    public List<Reservation> fetchAllReservations() {
        List<Reservation> reservations = reservationRepo.fetchAllReservations();
        // Fetched reservations do not have brand, model and chosen accessories, so we set those before returning the list of reservations.
        populateReservations(reservations);
        return reservations;
    }

    public List<Reservation> fetchReservationsByCustomerUsername(String username) {
        // Fetched reservations do not have brand, model and chosen accessories, so we set those before returning the list of reservations.
        List<Reservation> reservations = reservationRepo.fetchReservationsByCustomerUsername(username);
        populateReservations(reservations);
        return reservations;
    }

    private void populateReservations(List<Reservation> reservations) {
        // Loop through each reservation setting accessories and getting the brand + model
        for (Reservation reservation : reservations) {
            int id = reservation.getReservation_id();
            reservation.setAccessories(accessoryService.fetchAllChosenAccessoriesById(id));
            String brandandmodel = motorhomeService.fetchBrandAndModelByReservationId(id);
            // If brand and model is null it means the associated motorhome was deleted from the system
            if (brandandmodel == null) {
                reservation.setBrand_model("Autocamperen er slettet");
            } else {
                reservation.setBrand_model(brandandmodel);
            }
        }
    }

    public Reservation fetchReservationById(int id) {
        return reservationRepo.fetchReservationById(id);
    }

    public void createReservation(Reservation r) {
        // Create the reservation and set the associated motorhome to unavailable
        reservationRepo.createReservation(r);
        motorhomeService.setMotorhomeUnavailable(r.getLicense_plate());

        // After creating the reservation, find the id in order to link the accessories and the invoice to the reservation
        int reservation_id = findNewestReservationId();
        invoiceService.createInvoiceFromReservation(r, reservation_id);
        List<String> chosen_accessories = r.getAccessories();
        // Loops through the list of chosen accessories and creates each one
        if (chosen_accessories != null) {
            for (String accessory : chosen_accessories) {
                accessoryService.createAccessoryForReservation(reservation_id, accessory);
            }
        }
    }

    public void editReservation(Reservation r, String license ) {
        Invoice invoice = new Invoice();
        invoice.setReservation_id(r.getReservation_id());
        // Compare license plates between post edit and pre edit. If they're not the same, a new one was chosen, so set old to available
        // then set reservation license plate to the new one, update the reservation and set the new motorhome to unavailable
        if (!(r.getLicense_plate().equals(license))) {
            motorhomeService.setMotorhomeAvailable(r.getLicense_plate());
            r.setLicense_plate(license);
            reservationRepo.editReservation(r);
            motorhomeService.setMotorhomeUnavailable(license);
        }
        // Included 'else if' instead of having only the if block with editReservation(r) outside so that it doesn't set availability before trying to update (in case it fails)
        else if (r.getLicense_plate().equals(license)) {
            reservationRepo.editReservation(r);
        }

        invoiceService.updateInvoiceFromReservation(r);

        /* Delete all accessories tied to a reservation before creating any chosen ones. Not ideal if no changes were made to accessories, but the simplest solution. */
        accessoryService.deleteAccessoryInReservation(r.getReservation_id());

        List<String> chosen_accessories = r.getAccessories();
        /*Loops through the list of chosen accessories and creates each one linked to the reservation id */
        if (chosen_accessories != null) {
            for (String accessory : chosen_accessories) {
                accessoryService.createAccessoryForReservation(r.getReservation_id(), accessory);
            }
        }
    }

    public void deleteReservation(int id) {
        // Before deleting a reservation, fetch the motorhome associated with that reservation in order to set it available.
        Reservation reservation = reservationRepo.fetchReservationById(id);
        reservationRepo.deleteReservation(id);
        motorhomeService.setMotorhomeAvailable(reservation.getLicense_plate());
    }

    public int findNewestReservationId() {
        return reservationRepo.findNewestReservationId();
    }

    public void setReservationFinished(String license) {
        reservationRepo.setReservationFinished(license);
    }

    public boolean compareDates(String first_date, String second_date) {
        // Takes two string dates as input. Returns true if the first date is before the second. Otherwise false.
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate date1 = LocalDate.parse(first_date, dtf);
            LocalDate date2 = LocalDate.parse(second_date, dtf);
            return date1.isBefore(date2);
        }
        catch (DateTimeParseException e) {
            e.getMessage();
        }
        return false;
    }


}
