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
        String sql = "INSERT INTO accessory VALUES (?, ?, ?, ?, ?, ?)";
        template.update(sql, a.getAccessory_id(), a.isBike_rack(), a.isBed_linen(), a.isChild_seat(), a.isPicnic_table(), a.isChairs());
    }

    public void updateAccessory(Accessory a) {
        String sql = "UPDATE accessory SET bike_rack=?, bed_linen=?, child_seat=?, picnic_table=?, chairs=? WHERE accessory_id=?";
        template.update(sql, a.getAccessory_id(), a.isBike_rack(), a.isBed_linen(), a.isChild_seat(), a.isPicnic_table(), a.isChairs(), a.getAccessory_id());
    }

    public void deleteAccessory(int id) {
        String sql = "DELETE FROM accessory WHERE accessory_id = ?";
        template.update(sql, id);
    }
}
