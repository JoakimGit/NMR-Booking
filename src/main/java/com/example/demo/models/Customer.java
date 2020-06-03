package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.Id;

//Lavet af Emil
@Entity
public class Customer {

    @Id
    private int customer_id;
    private String first_name;
    private String last_name;
    private String user_name;
    private String signup_date;
    private String email;
    private String phonenumber;

    public Customer(int customer_id, String first_name, String last_name, String user_name, String signup_date, String email, String phonenumber) {
        this.customer_id = customer_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.user_name = user_name;
        this.signup_date = signup_date;
        this.email = email;
        this.phonenumber = phonenumber;
    }

    public Customer() {}

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getSignup_date() {
        return signup_date;
    }

    public void setSignup_date(String signup_date) {
        this.signup_date = signup_date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customer_id=" + customer_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", user_name='" + user_name + '\'' +
                ", signup_date='" + signup_date + '\'' +
                ", email='" + email + '\'' +
                ", phonenumber=" + phonenumber +
                '}';
    }
}
