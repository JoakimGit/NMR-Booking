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

    public List<Motorhome> fetchAllMotorhomesByBrandAndModel(String brand, String model) {
        String sql ="SELECT * FROM motorhome WHERE brand=? AND model=?";
        RowMapper<Motorhome> rowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        return template.query(sql, rowMapper, brand, model);
    }

    public List<Motorhome> fetchAllDistinctMotorhomes() {
        String sql = "SELECT price, brand, model, beds, file_path FROM motorhome GROUP BY price, brand, model, beds ORDER BY brand";
        RowMapper<Motorhome> rowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        return template.query(sql, rowMapper);
    }

    public List<Motorhome> fetchAllAvailableMotorhomes() {
        String sql = "SELECT min(motorhome_id) as motorhome_id, price, brand, model, available, beds FROM motorhome WHERE available=true GROUP BY price, brand, model, beds;";
        RowMapper<Motorhome> rowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        return template.query(sql, rowMapper);
    }

    public Motorhome fetchMotorhomeById(int motorhome_id) {
        String sql = "SELECT * FROM motorhome WHERE motorhome_id=?";
        RowMapper<Motorhome> rowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        return template.queryForObject(sql, rowMapper, motorhome_id);
    }

    public List<String> fetchMotorhomeBrandAndModel() {
        String sql = "SELECT CONCAT(brand, ' ~ ', model) AS brand_model FROM motorhome WHERE available=true GROUP BY brand_model;";
        return template.queryForList(sql, String.class);
    }

    public Motorhome fetchAvailableMotorhomeByBrandAndModel(String brand, String model) {
        String sql = "SELECT min(motorhome_id) as motorhome_id, price, brand, model, available, beds, license_plate FROM motorhome WHERE brand=? AND model=? AND available=true";
        RowMapper<Motorhome> rowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        return template.queryForObject(sql, rowMapper, brand, model);
    }

    public Motorhome fetchMotorhomeByLicense(String license_plate) {
        String sql = "SELECT * FROM motorhome WHERE license_plate=?";
        RowMapper<Motorhome> rowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        return template.queryForObject(sql, rowMapper, license_plate);
    }

    public void createMotorhome(Motorhome m) {
        String sql = "INSERT INTO motorhome VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, m.getMotorhome_id(), m.getPrice(), m.getBrand(), m.getModel(), m.isAvailable(), m.getBeds(), m.getFile_path(), m.getLicense_plate());
    }

    public void updateMotorhome(Motorhome m){
        String sql = "UPDATE motorhome SET price=?, brand=?, model=?, available=?, beds=?, file_path=?, license_plate=? WHERE motorhome_id=?";
        template.update(sql, m.getPrice(), m.getBrand(), m.getModel(), m.isAvailable(), m.getBeds(), m.getFile_path(), m.getLicense_plate(), m.getMotorhome_id());
    }

    public void deleteMotorhome(int motorhome_id) {
        String sql = "DELETE FROM motorhome WHERE motorhome_id=?";
        template.update(sql, motorhome_id);
    }

    public void setMotorhomeUnavailable(String license) {
        String sql = "UPDATE motorhome SET available=false WHERE license_plate=?";
        template.update(sql, license);
    }

    public void setMotorhomeAvailable(String license) {
        String sql = "UPDATE motorhome SET available=true WHERE license_plate=?";
        template.update(sql, license);
    }


}