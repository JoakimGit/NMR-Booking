package com.example.demo.repositories;


import com.example.demo.models.Motorhome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MotorhomeRepo {

    @Autowired
    JdbcTemplate template;

    public List<Motorhome> fetchAllMotorhomes() {
        String sql = "SELECT* FROM motorhome";
        RowMapper<Motorhome> rowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        return template.query(sql, rowMapper);
    }

    public Motorhome fetchMotorhomeById(int motorhome_id) {
        String sql = "SELECT * FROM motorhome WHERE motorhome_id=?";
        RowMapper<Motorhome> rowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        return template.queryForObject(sql, rowMapper, motorhome_id);
    }

    public void updateMotorhome(Motorhome m){
        String sql = "UPDATE motorhome SET price=?, brand=?, model=?, available=?, bed=? WHERE motorhome_id=?";
        template.update(sql, m.getBeds(), m.isAvailable(), m.getPrice(), m.getModel(), m.getBrand(), m.getMotorhome_id());
    }


    public void createMotorhome(Motorhome m) {
        String sql = "INSERT INTO motorhome VALUES(?,?,?,?,?,?)";
        template.update(sql, m.getMotorhome_id(), m.getBrand(), m.getModel(), m.getPrice(), m.getBeds(), m.isAvailable());
    }

    public void deleteMotorhome(int motorhome_id) {
        String sql = "DELETE FROM item WHERE motorhome_id=?";
        template.update(sql, motorhome_id);
    }

}