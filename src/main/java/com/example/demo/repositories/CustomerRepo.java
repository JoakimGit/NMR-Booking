package com.example.demo.repositories;

import com.example.demo.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//Lavet af Emil
@Repository
public class CustomerRepo {

    @Autowired
    private JdbcTemplate template;

    public List<Customer> fetchAllCustomers() {
        String sql = "SELECT * FROM customer";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        return template.query(sql, rowMapper);
    }

    public Customer fetchCustomerById(int id) {
        String sql = "SELECT * FROM customer WHERE customer_id=?";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        return template.queryForObject(sql, rowMapper, id);
    }

    public void createCustomer(Customer c) {
        String sql = "INSERT INTO customer VALUES (?,?,?,?,?,?,?)";
        template.update(sql, c.getCustomer_id(), c.getFirst_name(), c.getLast_name(), c.getUser_name(), c.getSignup_date(), c.getEmail(), c.getPhonenumber());
    }

    public void editCustomer(Customer c) {
        String sql = "UPDATE customer SET first_name=?, last_name=?, user_name=?, signup_date=?, email=?, phonenumber=? WHERE customer_id=?";
        template.update(sql, c.getFirst_name(), c.getLast_name(), c.getUser_name(), c.getSignup_date(), c.getEmail(), c.getPhonenumber(), c.getCustomer_id());
    }

    public void deleteCustomer(int id) {
        String sql = "DELETE FROM customer WHERE customer_id=?";
        template.update(sql, id);
    }

    public List<String> fetchUserNameFromCustomer() {
        String sql = "SELECT user_name FROM customer";
        return template.queryForList(sql, String.class);
    }

    public List<String> fetchEmailFromCustomer() {
        String sql = "SELECT email FROM customer";
        return template.queryForList(sql, String.class);
    }

    public List<String> fetchPhoneNumberFromCustomer() {
        String sql = "SELECT phonenumber FROM customer";
        return template.queryForList(sql, String.class);
    }

    public List<String> fetchUserNameFromOtherCustomer(int id) {
        String sql = "SELECT user_name FROM customer WHERE customer_id!=?";
        return template.queryForList(sql, String.class, id);
    }

    public List<String> fetchEmailFromOtherCustomer(int id) {
        String sql = "SELECT email FROM customer WHERE customer_id!=?";
        return template.queryForList(sql, String.class, id);
    }

    public List<String> fetchPhoneNumberFromOtherCustomer(int id) {
        String sql = "SELECT phonenumber FROM customer WHERE customer_id!=?";
        return template.queryForList(sql, String.class, id);
    }
}
