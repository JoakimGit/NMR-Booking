package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.Id;

//Lavet af Daniel
@Entity
public class Motorhome {

    @Id
    private int motorhome_id;
    private int price;
    private String brand;
    private String model;
    private boolean available;
    private int beds;
    private String file_path;
    private String license_plate;
    private int motorhometype_id;

    public Motorhome() {
    }

    public Motorhome(int motorhome_id, int price, String brand, String model, boolean available, int beds, String file_path, String license_plate, int motorhometype_id) {
        this.motorhome_id = motorhome_id;
        this.price = price;
        this.brand = brand;
        this.model = model;
        this.available = available;
        this.beds = beds;
        this.file_path = file_path;
        this.license_plate = license_plate;
        this.motorhometype_id = motorhometype_id;
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

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getLicense_plate() {
        return license_plate;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }

    public int getMotorhometype_id() {
        return motorhometype_id;
    }

    public void setMotorhometype_id(int motorhometype_id) {
        this.motorhometype_id = motorhometype_id;
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
                ", file_path='" + file_path + '\'' +
                ", license_plate='" + license_plate + '\'' +
                ", motorhometype_id=" + motorhometype_id +
                '}';
    }
}