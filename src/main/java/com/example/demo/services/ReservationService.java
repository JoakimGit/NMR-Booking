package com.example.demo.services;

import com.example.demo.models.Reservation;
import com.example.demo.repositories.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    ReservationRepo reservationRepo;

    @Autowired
    AccessoryService accessoryService;

    public Reservation fetchReservationById(int id) {
        return reservationRepo.fetchReservationById(id);
    }

    public List<Reservation> fetchAllReservations() {
        List<Reservation> reservations = reservationRepo.fetchAllReservations();
        for (Reservation reservation : reservations) {
            int id = reservation.getReservation_id();
            reservation.setAccessories(accessoryService.fetchAllChosenAccessoriesById(id));
        }
        return reservations;
    }

    public void createReservation(Reservation r) {
        reservationRepo.createReservation(r);

        int reservation_id = findNewestReservationId();
        List<String> chosen_accessories = r.getAccessories();

        if (chosen_accessories != null) {
            for (String accessory : chosen_accessories) {
                accessoryService.createAccessoryForReservation(reservation_id, accessory);
            }
        }
    }

    public void editReservation(Reservation r) {
        reservationRepo.editReservation(r);
        accessoryService.deleteAccessoryInReservation(r.getReservation_id());

        List<String> chosen_accessories = r.getAccessories();

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
}
