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

    public List<Reservation> fetchAllReservations() {
        String sql = "SELECT * FROM reservation";
        RowMapper<Reservation> rowMapper = new BeanPropertyRowMapper<>(Reservation.class);
        return template.query(sql, rowMapper);
    }

    public void addReservation(Reservation r) {
        String sql = "INSERT INTO reservation (reservation_id, location, pickup_date, dropoff_date, accessories) VALUES (?, ?, ?)";
        template.update(sql, r.getReservation_id(), r.getLocation(), r.getPickup_date(), r.getDropoff_date(), r.getAccessories());
    }

    public Reservation findReservationById(int id) {
    String sql = "SELECT * FROM reservation WHERE id=?";
    RowMapper<Reservation> rowMapper = new BeanPropertyRowMapper<>(Reservation.class);
    return template.queryForObject(sql, rowMapper, id);
    }

    public void updateReservation(Reservation r) {
    String sql = "UPDATE reservation SET location=?, pickup_date=?, dropoff_date=?, accessories=? WHERE id=?";
    template.update(sql, r.getLocation(),r.getPickup_date(), r.getDropoff_date(), r.getAccessories(),r.getReservation_id());
    }

    public void deleteReservation(int id) {
    String sql = "DELETE FROM reservation WHERE id=?";
    template.update(sql, id);
    }





}
