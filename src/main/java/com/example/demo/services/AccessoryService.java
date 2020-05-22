package com.example.demo.services;

import com.example.demo.models.Accessory;
import com.example.demo.repositories.AccessoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccessoryService {

    @Autowired
    AccessoryRepo accessoryRepo;

    public Accessory fetchAccessoryById(int id) {
        return accessoryRepo.fetchAccessoryById(id);
    }

    public List<Accessory> fetchAllAccessories() {
        return accessoryRepo.fetchAllAccessories();
    }

    public void createAccessory(Accessory a) {
        accessoryRepo.createAccessory(a);
    }

    public void updateAccessory(Accessory a) {
        accessoryRepo.updateAccessory(a);
    }

    public void deleteAccessory(int id) {
        accessoryRepo.deleteAccessory(id);
    }

}
