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

    public List<Reservation> fetchAllReservations() {
    return reservationRepo.fetchAllReservations();
    }

    public void createReservation(Reservation r) {
    reservationRepo.createReservation(r);

    }

    public Reservation findReservationById(int id) {
    return reservationRepo.findReservationById(id);

    }

    public void editReservation(Reservation r) {
    reservationRepo.editReservation(r);
    }

    public void deleteReservation(int id) {
    reservationRepo.deleteReservation(id);
    }
}
