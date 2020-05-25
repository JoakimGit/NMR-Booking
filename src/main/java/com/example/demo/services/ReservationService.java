package com.example.demo.services;

import com.example.demo.models.Accessory;
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
        return reservationRepo.fetchAllReservations();
    }

    public void createReservation(Reservation r, Accessory a) {
        reservationRepo.createReservation(r);

        int reservation_id = findNewestReservationId();
        List<String> chosen_accessories = a.getChosen_accessories();

        if (chosen_accessories != null) {
            for (String accessory : chosen_accessories) {
                accessoryService.createAccessoryForReservation(reservation_id, accessory);
            }
        }
    }

    public void editReservation(Reservation r, Accessory a) {
        reservationRepo.editReservation(r);

        List<String> chosen_accessories = a.getChosen_accessories();

        accessoryService.deleteAccessoryInReservation(r.getReservation_id());

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
