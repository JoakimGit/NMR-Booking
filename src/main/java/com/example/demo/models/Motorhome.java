package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Motorhome {

    @Id
    private int motorhome_id;
    private int price;
    private String brand;
    private String model;
    private boolean available;
    private int beds;

    public Motorhome() {
    }

    public Motorhome(int motorhome_id, int price, String brand, String model, boolean available, int beds) {
        this.motorhome_id = motorhome_id;
        this.price = price;
        this.brand = brand;
        this.model = model;
        this.available = available;
        this.beds = beds;
    }

    public int getMotorhome_id() {
        return motorhome_id;
    }

    public void setMotorhome_id(int motorhome_id) {
        this.motorhome_id = motorhome_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    @Override
    public String toString() {
        return "Motorhome{" +
                "motorhome_id=" + motorhome_id +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", available=" + available +
                ", beds=" + beds +
                '}';
    }
}
