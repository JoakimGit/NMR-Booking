package com.example.demo.services;


import com.example.demo.models.Motorhome;
import com.example.demo.repositories.MotorhomeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotorhomeService {
    @Autowired
    MotorhomeRepo motorhomeRepo;
    public List<Motorhome>fetchAllDistinctMotorhomes(){
        return motorhomeRepo.fetchAllDistinctMotorhomes();
    }

    public Motorhome fetchMotorhomeByID(int motorhome_id){
        return motorhomeRepo.fetchMotorhomeById(motorhome_id);
    }

    public void updateMotorhome(Motorhome m){
        motorhomeRepo.updateMotorhome(m);
    }

    public void createMotorhome(Motorhome m){
        motorhomeRepo.createMotorhome(m);
    }

    public void deleteMotorhome(int motorhome_id){
    motorhomeRepo.deleteMotorhome(motorhome_id);
    }

    public List <Motorhome> fetchAllMotorhomesByBrandAndModel(String brand, String model){
        return motorhomeRepo.fetchAllMotorhomesByBrandAndModel(brand, model);
    }

    public List<String> fetchMotorhomeBrandAndModel() {
        return motorhomeRepo.fetchMotorhomeBrandAndModel();
    }

    public List<Motorhome> fetchAllAvailableMotorhomes() {
        return motorhomeRepo.fetchAllAvailableMotorhomes();
    }

    public Motorhome fetchAvailableMotorhomeByBrandAndModel(String brand, String model) {
        return motorhomeRepo.fetchAvailableMotorhomeByBrandAndModel(brand, model);
    }

    public void setMotorhomeUnavailable(String license) {
        motorhomeRepo.setMotorhomeUnavailable(license);
    }

    public void setMotorhomeAvailable(String license) {
        motorhomeRepo.setMotorhomeAvailable(license);
    }

    public Motorhome fetchMotorhomeByLicense(String license_plate) {
        return motorhomeRepo.fetchMotorhomeByLicense(license_plate);
    }

}


