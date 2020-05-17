package com.example.demo.repositories;

import com.example.demo.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepoJPA extends JpaRepository<Reservation, Integer> {
}
