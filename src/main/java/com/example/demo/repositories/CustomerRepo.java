package com.example.demo.repositories;

import com.example.demo.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepo {

    @Autowired
    private JdbcTemplate template;

    public List<Customer> fetchAllCustomers() {
        String sql = "SELECT * FROM customer";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        return template.query(sql, rowMapper);
    }

    public Customer fetchCustomerById(int customer_id) {
        String sql = "SELECT * FROM customer WHERE customer_id=?";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        return template.queryForObject(sql, rowMapper, customer_id);
    }

    public void createCustomer(Customer c) {
        String sql = "INSERT INTO customer(first_name, last_name, user_name, signup_date, email, phonenumber) VALUES (?,?,?,?,?,?)";
        template.update(sql, c.getFirst_name(), c.getLast_name(), c.getUser_name(), c.getSignup_date(), c.getEmail(), c.getPhonenumber());
    }

    public void editCustomer(Customer c) {
        String sql = "UPDATE customer SET first_name=?, last_name=?, user_name=?, signup_date=?, email=?, phonenumber=? WHERE customer_id=?";
        template.update(sql, c.getFirst_name(), c.getLast_name(), c.getUser_name(), c.getSignup_date(), c.getEmail(), c.getPhonenumber(), c.getCustomer_id());
    }

    public void deleteCustomer(int customer_id) {
        String sql = "DELETE FROM customer WHERE customer_id=?";
        template.update(sql, customer_id);
    }
}
