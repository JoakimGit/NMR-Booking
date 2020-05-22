package com.example.demo.models;

public class Accessory {

    private int accessory_id;
    private boolean bike_rack;
    private boolean bed_linen;
    private boolean child_seat;
    private boolean picnic_table;
    private boolean chairs;
    private int reservation_id;

    public Accessory() {
    }

    public Accessory(int accessory_id, boolean bike_rack, boolean bed_linen, boolean child_seat, boolean picnic_table, boolean chairs, int reservation_id) {
        this.accessory_id = accessory_id;
        this.bike_rack = bike_rack;
        this.bed_linen = bed_linen;
        this.child_seat = child_seat;
        this.picnic_table = picnic_table;
        this.chairs = chairs;
        this.reservation_id = reservation_id;
    }

    public int getAccessory_id() {
        return accessory_id;
    }

    public void setAccessory_id(int accessory_id) {
        this.accessory_id = accessory_id;
    }

    public boolean isBike_rack() {
        return bike_rack;
    }

    public void setBike_rack(boolean bike_rack) {
        this.bike_rack = bike_rack;
    }

    public boolean isBed_linen() {
        return bed_linen;
    }

    public void setBed_linen(boolean bed_linen) {
        this.bed_linen = bed_linen;
    }

    public boolean isChild_seat() {
        return child_seat;
    }

    public void setChild_seat(boolean child_seat) {
        this.child_seat = child_seat;
    }

    public boolean isPicnic_table() {
        return picnic_table;
    }

    public void setPicnic_table(boolean picnic_table) {
        this.picnic_table = picnic_table;
    }

    public boolean isChairs() {
        return chairs;
    }

    public void setChairs(boolean chairs) {
        this.chairs = chairs;
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }
}
