package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Reservation {

    @Id
    private int reservation_id;
    private String location;
    private String pickup_date;
    private String dropoff_date;

    public Reservation(int reservation_id, String location, String pickup_date, String dropoff_date) {
    this.reservation_id = reservation_id;
    this.location = location;
    this.pickup_date = pickup_date;
    this.dropoff_date = dropoff_date;
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
}
