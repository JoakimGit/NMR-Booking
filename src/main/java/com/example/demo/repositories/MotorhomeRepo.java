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
        String sql = "SELECT * FROM motorhome";
        RowMapper<Motorhome> rowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        return template.query(sql, rowMapper);
    }

    public List<Motorhome> fetchAllDistinctMotorhomes() {
        String sql = "SELECT min(motorhome_id) as motorhome_id, price, brand, model, available, beds FROM motorhome GROUP BY price, brand, model, available, beds";
        RowMapper<Motorhome> rowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        return template.query(sql, rowMapper);
    }

    public Motorhome fetchMotorhomeById(int motorhome_id) {
        String sql = "SELECT * FROM motorhome WHERE motorhome_id=?";
        RowMapper<Motorhome> rowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        return template.queryForObject(sql, rowMapper, motorhome_id);
    }

    public void updateMotorhome(Motorhome m){
        String sql = "UPDATE motorhome SET price=?, brand=?, model=?, available=?, beds=? WHERE motorhome_id=?";
        template.update(sql, m.getMotorhome_id(), m.getPrice(), m.getBrand(), m.getModel(), m.isAvailable(), m.getBeds());
    }

    public void createMotorhome(Motorhome m) {
        String sql = "INSERT INTO motorhome VALUES(?,?,?,?,?,?)";
        template.update(sql, m.getMotorhome_id(), m.getPrice(), m.getBrand(), m.getModel(), m.isAvailable(), m.getBeds());
    }

    public void deleteMotorhome(int motorhome_id) {
        String sql = "DELETE FROM motorhome WHERE motorhome_id=?";
        template.update(sql, motorhome_id);
    }
}