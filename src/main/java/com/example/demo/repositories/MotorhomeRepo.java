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

    public List<Motorhome> fetchAllMotorhomeTypes() {
        String sql = "SELECT * FROM motorhome_type";
        RowMapper<Motorhome> rowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        return template.query(sql, rowMapper);
    }

    public List<Motorhome> fetchAllMotorhomesByTypeId(int id) {
        String sql ="SELECT motorhome_id, license_plate, available, motorhome.motorhometype_id, price, brand, model, beds, file_path FROM motorhome " +
                "INNER JOIN motorhome_type ON motorhome.motorhometype_id = motorhome_type.motorhometype_id WHERE motorhome_type.motorhometype_id=?";
        RowMapper<Motorhome> rowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        return template.query(sql, rowMapper, id);
    }

    public List<Motorhome> fetchAllAvailableMotorhomes() {
        String sql = "SELECT motorhome_id, license_plate, brand, model FROM motorhome INNER JOIN motorhome_type ON motorhome.motorhometype_id = " +
                "motorhome_type.motorhometype_id WHERE available=true GROUP BY brand, model";
        RowMapper<Motorhome> rowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        return template.query(sql, rowMapper);
    }

    public List<Motorhome> fetchAllOtherAvailableMotorhomes(String model) {
        String sql = "SELECT motorhome_id, license_plate, brand, model FROM motorhome INNER JOIN motorhome_type ON motorhome.motorhometype_id = " +
                "motorhome_type.motorhometype_id WHERE available=true AND model!=? GROUP BY brand, model";
        RowMapper<Motorhome> rowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        return template.query(sql, rowMapper, model);
    }

    public String fetchBrandAndModelByReservationId(int id) {
        String sql = "SELECT CONCAT(brand, ' - ', model) AS brand_model FROM motorhome_type JOIN motorhome ON motorhome_type.motorhometype_id " +
                "= motorhome.motorhometype_id JOIN reservation ON motorhome.license_plate = reservation.license_plate WHERE reservation_id=?";
        List<String> brand_model = template.queryForList(sql, String.class, id);
        if (brand_model.isEmpty()) {
            return null;
        } else {
            return brand_model.get(0);
        }
    }

    public Motorhome fetchMotorhomeTypeById(int id) {
        String sql = "SELECT * FROM motorhome_type WHERE motorhometype_id=?";
        RowMapper<Motorhome> rowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        return template.queryForObject(sql, rowMapper, id);
    }

    public Motorhome fetchMotorhomeById(int motorhome_id) {
        String sql = "SELECT * FROM motorhome WHERE motorhome_id=?";
        RowMapper<Motorhome> rowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        return template.queryForObject(sql, rowMapper, motorhome_id);
    }

    public Motorhome fetchMotorhomeByLicense(String license_plate) {
        String sql = "SELECT motorhome_id, license_plate, available, motorhome.motorhometype_id, price, brand, model, beds, file_path FROM motorhome INNER JOIN " +
                "motorhome_type ON motorhome.motorhometype_id = motorhome_type.motorhometype_id WHERE license_plate=?";
        RowMapper<Motorhome> rowMapper = new BeanPropertyRowMapper<>(Motorhome.class);
        return template.queryForObject(sql, rowMapper, license_plate);
    }

    public void createMotorhome(Motorhome m) {
        String sql = "INSERT INTO motorhome VALUES(?, ?, ?, ?)";
        template.update(sql, m.getMotorhome_id(), m.getLicense_plate(), m.isAvailable(), m.getMotorhometype_id());
    }

    public void createMotorhomeType(Motorhome m) {
        String sql = "INSERT INTO motorhome_type VALUES(?, ?, ?, ?, ?, ?)";
        template.update(sql, m.getMotorhometype_id(), m.getPrice(), m.getBrand(), m.getModel(), m.getBeds(), m.getFile_path());
    }

    public void updateMotorhome(Motorhome m){
        String sql = "UPDATE motorhome SET available=?, license_plate=? WHERE motorhome_id=?";
        template.update(sql, m.isAvailable(), m.getLicense_plate(), m.getMotorhome_id());
    }

    public void updateMotorhomeType(Motorhome m){
        String sql = "UPDATE motorhome_type SET price=?, brand=?, model=?, beds=?, file_path=? WHERE motorhometype_id=?";
        template.update(sql, m.getPrice(), m.getBrand(), m.getModel(), m.getBeds(), m.getFile_path(), m.getMotorhometype_id());
    }

    public void deleteMotorhome(int motorhome_id) {
        String sql = "DELETE FROM motorhome WHERE motorhome_id=?";
        template.update(sql, motorhome_id);
    }

    public void deleteMotorhomeType(int id) {
        String sql = "DELETE FROM motorhome_type WHERE motorhometype_id=?";
        template.update(sql, id);
    }

    public void setMotorhomeAvailable(String license) {
        String sql = "UPDATE motorhome SET available=true WHERE license_plate=?";
        template.update(sql, license);
    }

    public void setMotorhomeUnavailable(String license) {
        String sql = "UPDATE motorhome SET available=false WHERE license_plate=?";
        template.update(sql, license);
    }

    public List<String> fetchLicensePlateFromMotorhome() {
        String sql = "SELECT license_plate FROM motorhome";
        return template.queryForList(sql, String.class);
    }

    public List<String> fetchLicensePlateFromOtherMotorhome(int id) {
        String sql = "SELECT license_plate FROM motorhome WHERE motorhome_id!=?";
        return template.queryForList(sql, String.class, id);
    }
}