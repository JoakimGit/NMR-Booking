package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.Id;

//Lavet af Joakim
@Entity
public class Accessory {

    @Id
    private int accessory_id;
    private String accessory_name;

    public Accessory() {
    }

    public Accessory(int accessory_id, String accessory_name) {
        this.accessory_id = accessory_id;
        this.accessory_name = accessory_name;
    }

    public int getAccessory_id() {
        return accessory_id;
    }

    public void setAccessory_id(int accessory_id) {
        this.accessory_id = accessory_id;
    }

    public String getAccessory_name() {
        return accessory_name;
    }

    public void setAccessory_name(String accessory_name) {
        this.accessory_name = accessory_name;
    }
}
