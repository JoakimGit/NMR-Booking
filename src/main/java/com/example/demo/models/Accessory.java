package com.example.demo.models;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Accessory {

    @Id
    private int accessory_id;
    private String accessory_name;
    @ElementCollection
    private List<String> chosen_accessories;

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

    public List<String> getChosen_accessories() {
        return chosen_accessories;
    }

    public void setChosen_accessories(List<String> chosen_accessories) {
        this.chosen_accessories = chosen_accessories;
    }
}
