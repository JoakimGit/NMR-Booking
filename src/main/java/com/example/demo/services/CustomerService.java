package com.example.demo.services;

import com.example.demo.models.Customer;
import com.example.demo.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Lavet af Emil
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

    public void createCustomer(Customer c) {
        customerRepo.createCustomer(c);
    }

    public void editCustomer(Customer c) {
        customerRepo.editCustomer(c);
    }

    public void deleteCustomer(int customer_id) {
        customerRepo.deleteCustomer(customer_id);
    }

    public void formatPhone(String phone, Customer customer) {
        // If user typed in number as xx-xx-xx-xx it will be formattted to xxxxxxxx
        if (phone.length() > 8) {
            phone = phone.replaceAll("[\\-]", "");
            customer.setPhonenumber(phone);
        }
    }

    // Methods used for checking for duplicates
    public boolean checkForDuplicateUserName(String user_name) {
        List<String> UserNameList = customerRepo.fetchUserNameFromCustomer();
        return UserNameList.contains(user_name);
    }

    public boolean checkForDuplicateEmail(String email) {
        List<String> emailList = customerRepo.fetchEmailFromCustomer();
        return emailList.contains(email);
    }

    public boolean checkForDuplicatePhoneNumber(String phonenumber) {
        List<String> phoneNumberList = customerRepo.fetchPhoneNumberFromCustomer();
        return phoneNumberList.contains(phonenumber);
    }

    public boolean checkForOtherDuplicateUserName(String user_name, int id) {
        List<String> UserNameList = customerRepo.fetchUserNameFromOtherCustomer(id);
        return UserNameList.contains(user_name);
    }

    public boolean checkForOtherDuplicateEmail(String email, int id) {
        List<String> emailList = customerRepo.fetchEmailFromOtherCustomer(id);
        return emailList.contains(email);
    }

    public boolean checkForOtherDuplicatePhoneNumber(String phonenumber, int id) {
        List<String> phoneNumberList = customerRepo.fetchPhoneNumberFromOtherCustomer(id);
        return phoneNumberList.contains(phonenumber);
    }
}