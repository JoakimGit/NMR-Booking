package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.Id;

//Lavet af Joakim
@Entity
public class Invoice {

    @Id
    private int invoice_id;
    private String invoice_type;
    private double total;
    private Integer reservation_id;

    public Invoice() {
    }

    public Invoice(int invoice_id, String invoice_type, double total, Integer reservation_id) {
        this.invoice_id = invoice_id;
        this.invoice_type = invoice_type;
        this.total = total;
        this.reservation_id = reservation_id;
    }

    public int getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Integer getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(Integer reservation_id) {
        this.reservation_id = reservation_id;
    }

    public String getInvoice_type() {
        return invoice_type;
    }

    public void setInvoice_type(String invoice_type) {
        this.invoice_type = invoice_type;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoice_id=" + invoice_id +
                ", invoice_type='" + invoice_type + '\'' +
                ", total=" + total +
                ", reservation_id=" + reservation_id +
                '}';
    }
}