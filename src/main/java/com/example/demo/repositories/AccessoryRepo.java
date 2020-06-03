package com.example.demo.repositories;

import com.example.demo.models.Accessory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccessoryRepo {

    @Autowired
    JdbcTemplate template;

    public Accessory fetchAccessoryById(int id) {
        String sql = "SELECT * FROM accessory WHERE accessory_id = ?";
        RowMapper<Accessory> rowMapper = new BeanPropertyRowMapper<>(Accessory.class);
        return template.queryForObject(sql, rowMapper, id);
    }

    public List<Accessory> fetchAllAccessories() {
        String sql = "SELECT * FROM accessory";
        RowMapper<Accessory> rowMapper = new BeanPropertyRowMapper<>(Accessory.class);
        return template.query(sql, rowMapper);
    }

    public void createAccessory(Accessory a) {
        String sql = "INSERT INTO accessory VALUES (?, ?)";
        template.update(sql, a.getAccessory_id(), a.getAccessory_name());
    }

    public void updateAccessory(Accessory a) {
        String sql = "UPDATE accessory SET accessory_name=? WHERE accessory_id=?";
        template.update(sql, a.getAccessory_name(), a.getAccessory_id());
    }

    public void deleteAccessory(int id) {
        String sql = "DELETE FROM accessory WHERE accessory_id = ?";
        template.update(sql, id);
    }

    public List<String> fetchAllAccessoryNames() {
        String sql = "SELECT accessory_name FROM accessory";
        return template.queryForList(sql, String.class);
    }

    public List<String> fetchAllChosenAccessoriesById(int reservation_id) {
        String sql = "SELECT accessory_name FROM accessory_in_reservation WHERE reservation_id=?";
        return template.queryForList(sql, String.class, reservation_id);
    }

    public void createAccessoryForReservation (int id, String name) {
        String sql = "INSERT INTO accessory_in_reservation VALUES (?, ?)";
        template.update(sql, id, name);
    }

    public void deleteAccessoryInReservation(int id) {
        String sql = "DELETE FROM accessory_in_reservation WHERE reservation_id = ?";
        template.update(sql, id);
    }

    public List<String> fetchAccessoryNameFromAccessory() {
        String sql = "SELECT accessory_name FROM accessory";
        return template.queryForList(sql, String.class);
    }

    public List<String> fetchAccessoryNameFromOtherAccessory(int id) {
        String sql = "SELECT accessory_name FROM accessory WHERE accessory_id!=?";
        return template.queryForList(sql, String.class, id);
    }
}
