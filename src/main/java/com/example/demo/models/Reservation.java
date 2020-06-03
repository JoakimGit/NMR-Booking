package com.example.demo.models;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

//Lavet af Magnus
@Entity
public class Reservation {

    @Id
    private int reservation_id;
    private String location;
    private String pickup_date;
    private String dropoff_date;
    private String season;
    private String user_name;
    private String license_plate;
    private boolean status;

    @ElementCollection
    private List<String> accessories;
    private String brand_model;

    public Reservation(int reservation_id, String location, String pickup_date, String dropoff_date, String season, String user_name, String license_plate, boolean status) {
        this.reservation_id = reservation_id;
        this.location = location;
        this.pickup_date = pickup_date;
        this.dropoff_date = dropoff_date;
        this.season = season;
        this.user_name = user_name;
        this.license_plate = license_plate;
        this.status = status;
    }

    public Reservation() {}

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPickup_date() {
        return pickup_date;
    }

    public void setPickup_date(String pickup_date) {
        this.pickup_date = pickup_date;
    }

    public String getDropoff_date() {
        return dropoff_date;
    }

    public void setDropoff_date(String dropoff_date) {
        this.dropoff_date = dropoff_date;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public List<String> getAccessories() {
        return accessories;
    }

    public void setAccessories(List<String> accessories) {
        this.accessories = accessories;
    }

    public String getLicense_plate() {
        return license_plate;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }

    public String getBrand_model() {
        return brand_model;
    }

    public void setBrand_model(String brand_model) {
        this.brand_model = brand_model;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservation_id=" + reservation_id +
                ", location='" + location + '\'' +
                ", pickup_date='" + pickup_date + '\'' +
                ", dropoff_date='" + dropoff_date + '\'' +
                ", season='" + season + '\'' +
                ", user_name='" + user_name + '\'' +
                ", license_plate='" + license_plate + '\'' +
                ", status=" + status +
                ", accessories=" + accessories +
                ", brand_model='" + brand_model + '\'' +
                '}';
    }
}
