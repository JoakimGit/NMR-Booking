package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {

    @Id
    private int employee_id;
    private String first_name;
    private String last_name;
    private String cpr;
    private String email;
    private String job_title;
    private int phonenumber;
    private String employment_date;

    public Employee() {
    }

    public Employee(int employee_id, String first_name, String last_name, String cpr, String email, String job_title, int phonenumber, String employment_date) {
        this.employee_id = employee_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.cpr = cpr;
        this.email = email;
        this.job_title = job_title;
        this.phonenumber = phonenumber;
        this.employment_date = employment_date;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
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

    public String getCpr() {
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmployment_date() {
        return employment_date;
    }

    public void setEmployment_date(String employment_date) {
        this.employment_date = employment_date;
    }
}
