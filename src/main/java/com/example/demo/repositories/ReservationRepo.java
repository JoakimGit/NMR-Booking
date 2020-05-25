package com.example.demo.repositories;

import com.example.demo.models.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservationRepo {

    @Autowired
    JdbcTemplate template;

    public Reservation fetchReservationById(int id) {
        String sql = "SELECT * FROM reservation WHERE reservation_id=?";
        RowMapper<Reservation> rowMapper = new BeanPropertyRowMapper<>(Reservation.class);
        return template.queryForObject(sql, rowMapper, id);
    }

    public List<Reservation> fetchAllReservations() {
        String sql = "SELECT * FROM reservation";
        RowMapper<Reservation> rowMapper = new BeanPropertyRowMapper<>(Reservation.class);
        return template.query(sql, rowMapper);
    }

    public void createReservation(Reservation r) {
        String sql = "INSERT INTO reservation VALUES (?, ?, ?, ?)";
        template.update(sql, r.getReservation_id(), r.getLocation(), r.getPickup_date(), r.getDropoff_date());
    }

    public void editReservation(Reservation r) {
        String sql = "UPDATE reservation SET location=?, pickup_date=?, dropoff_date=? WHERE reservation_id=?";
        template.update(sql, r.getLocation(),r.getPickup_date(), r.getDropoff_date(), r.getReservation_id());
    }

    public void deleteReservation(int id) {
        String sql = "DELETE FROM reservation WHERE reservation_id=?";
        template.update(sql, id);
    }

    public int findNewestReservationId() {
        String sql = "SELECT LAST_INSERT_ID()";
        return template.queryForObject(sql, Integer.class);
    }
}
