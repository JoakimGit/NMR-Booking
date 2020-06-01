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

    public List<Reservation> fetchReservationsByCustomerUsername(String username) {
        String sql = "SELECT * FROM reservation WHERE user_name=?";
        RowMapper<Reservation> rowMapper = new BeanPropertyRowMapper<>(Reservation.class);
        return template.query(sql, rowMapper, username);
    }

    public Reservation fetchReservationById(int id) {
        String sql = "SELECT * FROM reservation WHERE reservation_id=?";
        RowMapper<Reservation> rowMapper = new BeanPropertyRowMapper<>(Reservation.class);
        return template.queryForObject(sql, rowMapper, id);
    }

    public void createReservation(Reservation r) {
        String sql = "INSERT INTO reservation VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, r.getReservation_id(), r.getLocation(), r.getPickup_date(), r.getDropoff_date(), r.getSeason(), r.getUser_name(), r.isStatus(), r.getLicense_plate() );
    }

    public void editReservation(Reservation r) {
        String sql = "UPDATE reservation SET location=?, pickup_date=?, dropoff_date=?, season=?, user_name=?, status=?, license_plate=? WHERE reservation_id=?";
        template.update(sql, r.getLocation(), r.getPickup_date(), r.getDropoff_date(), r.getSeason(), r.getUser_name(), r.isStatus(), r.getLicense_plate(), r.getReservation_id());
    }

    public void deleteReservation(int id) {
        String sql = "DELETE FROM reservation WHERE reservation_id=?";
        template.update(sql, id);
    }

    public int findNewestReservationId() {
        String sql = "SELECT LAST_INSERT_ID()";
        return template.queryForObject(sql, Integer.class);
    }

    public void setReservationFinished(String license) {
        String sql = "UPDATE reservation SET status=false WHERE license_plate=?";
        template.update(sql, license);
    }
}
