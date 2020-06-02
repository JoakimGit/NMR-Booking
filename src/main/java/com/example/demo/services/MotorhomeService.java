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

    @Autowired
    ReservationService reservationService;

    public List<Motorhome>fetchAllMotorhomeTypes(){
        return motorhomeRepo.fetchAllMotorhomeTypes();
    }

    public List <Motorhome> fetchAllMotorhomesByTypeId(int id){
        return motorhomeRepo.fetchAllMotorhomesByTypeId(id);
    }

    public List<Motorhome> fetchAllAvailableMotorhomes() {
        return motorhomeRepo.fetchAllAvailableMotorhomes();
    }

    public List<Motorhome> fetchAllOtherAvailableMotorhomes(String model) {
        return motorhomeRepo.fetchAllOtherAvailableMotorhomes(model);
    }

    public String fetchBrandAndModelByReservationId(int id) {
        return motorhomeRepo.fetchBrandAndModelByReservationId(id);
    }

    public Motorhome fetchMotorhomeTypeById(int id) {
        return motorhomeRepo.fetchMotorhomeTypeById(id);
    }

    public Motorhome fetchMotorhomeByID(int motorhome_id){
        return motorhomeRepo.fetchMotorhomeById(motorhome_id);
    }

    public Motorhome fetchMotorhomeByLicense(String license_plate) {
        return motorhomeRepo.fetchMotorhomeByLicense(license_plate);
    }

    public void createMotorhomeType(Motorhome m) {
        motorhomeRepo.createMotorhomeType(m);
    }

    public void createMotorhome(Motorhome m){
        motorhomeRepo.createMotorhome(m);
    }

    public void updateMotorhomeType(Motorhome m) {
        motorhomeRepo.updateMotorhomeType(m);
    }

    public void updateMotorhome(Motorhome m){
        motorhomeRepo.updateMotorhome(m);
    }

    public void deleteMotorhomeType(int id) {
        List<Motorhome> motorhomes = fetchAllMotorhomesByTypeId(id);
        for (Motorhome motorhome : motorhomes) {
            reservationService.setReservationFinished(motorhome.getLicense_plate());
        }
        motorhomeRepo.deleteMotorhomeType(id);
    }

    public void deleteMotorhome(int motorhome_id){
        Motorhome motorhome = fetchMotorhomeByID(motorhome_id);
        reservationService.setReservationFinished(motorhome.getLicense_plate());
        motorhomeRepo.deleteMotorhome(motorhome_id);
    }

    public void setMotorhomeAvailable(String license) {
        motorhomeRepo.setMotorhomeAvailable(license);
    }

    public void setMotorhomeUnavailable(String license) {
        motorhomeRepo.setMotorhomeUnavailable(license);
    }

    public boolean checkForDuplicateLicensePlate(String license_plate) {
        List<String> licensePlateList = motorhomeRepo.fetchLicensePlateFromMotorhome();
        if (licensePlateList.contains(license_plate)){
            return true;
        }
        return false;
    }


}


