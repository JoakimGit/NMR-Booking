package com.example.demo.services;

import com.example.demo.models.Customer;
import com.example.demo.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    public List<Customer> fetchAllCustomers() {
        return customerRepo.fetchAllCustomers();
    }

    public Customer fetchCustomerById(int customer_id) {
        return customerRepo.fetchCustomerById(customer_id);
    }

    public void editCustomer(Customer c) {
        formatPhone(c.getPhonenumber(), c);
        customerRepo.editCustomer(c);
    }

    public void createCustomer(Customer c) {
        formatPhone(c.getPhonenumber(), c);
        customerRepo.createCustomer(c);
    }

    public void deleteCustomer(int customer_id) {
        customerRepo.deleteCustomer(customer_id);
    }

    public void formatPhone(String phone, Customer customer) {
        if (phone.length() > 8) {
            phone = phone.replaceAll("[\\-]", "");
            customer.setPhonenumber(phone);
        }
    }
}
