package com.example.demo.services;

import com.example.demo.models.Motorhome;
import com.example.demo.models.Reservation;
import com.example.demo.repositories.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    ReservationRepo reservationRepo;

    @Autowired
    AccessoryService accessoryService;

    @Autowired
    MotorhomeService motorhomeService;

    public Reservation fetchReservationById(int id) {
        return reservationRepo.fetchReservationById(id);
    }

    public List<Reservation> fetchAllReservations() {
        List<Reservation> reservations = reservationRepo.fetchAllReservations();
        /* loop through each reservation, setting the accessories field with the chosen ones*/
        for (Reservation reservation : reservations) {
            int id = reservation.getReservation_id();
            reservation.setAccessories(accessoryService.fetchAllChosenAccessoriesById(id));
        }
        return reservations;
    }

    public void createReservation(Reservation r) {
        // Get a motorhome with the chosen brand/model
        // Set reservation.license_plate with that motorhome's license_plate
        // Set that motorhome available = false

        String[] splitBrandAndModel = r.getBrand_model().split("~");
        String brand = splitBrandAndModel[0].trim();
        String model = splitBrandAndModel[1].trim();
        Motorhome motorhome = motorhomeService.fetchAvailableMotorhomeByBrandAndModel(brand, model);
        r.setLicense_plate(motorhome.getLicense_plate());

        reservationRepo.createReservation(r);
        motorhomeService.setMotorhomeUnavailable(motorhome.getLicense_plate());

        /* After creating the reservation, find the id in order to link the accessories-to-be to the reservation*/
        int reservation_id = findNewestReservationId();

        List<String> chosen_accessories = r.getAccessories();
        /*Loops through the list of chosen accessories and creates each one linked to the reservation id */
        if (chosen_accessories != null) {
            for (String accessory : chosen_accessories) {
                accessoryService.createAccessoryForReservation(reservation_id, accessory);
            }
        }
    }

    public void editReservation(Reservation r) {
        // Get the motorhome associated with the license plate tied to the reservation. Then get the brand and model chosen in the edit.
        //System.out.println(r);
        Motorhome motorhome = motorhomeService.fetchMotorhomeByLicense(r.getLicense_plate());
        String[] splitBrandAndModel = r.getBrand_model().split("~");
        String brand = splitBrandAndModel[0].trim();
        String model = splitBrandAndModel[1].trim();
        /* Match the chosen brand/model against that belonging to the currently associated motorhome. If it's different, it means a new type was chosen.
           Set the old motorhome to available, get the new motorhome based on the chosen brand/model, set the reservation license plate to the new one.
           Finally set that motorhome unavailable and proceed with updating the reservation and accessories. */
        if (!(brand.equals(motorhome.getBrand()) && model.equals(motorhome.getModel()))) {
            motorhomeService.setMotorhomeAvailable(r.getLicense_plate());
            Motorhome new_motorhome = motorhomeService.fetchAvailableMotorhomeByBrandAndModel(brand, model);
            r.setLicense_plate(new_motorhome.getLicense_plate());
            //System.out.println(r);
            motorhomeService.setMotorhomeUnavailable(new_motorhome.getLicense_plate());

        }
        reservationRepo.editReservation(r);

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
        reservationRepo.deleteReservation(id);
    }

    public int findNewestReservationId() {
        return reservationRepo.findNewestReservationId();
    }

    public List<Reservation> fetchReservationsByCustomerUsername(String username) {
        return reservationRepo.fetchReservationsByCustomerUsername(username);
    }
}
