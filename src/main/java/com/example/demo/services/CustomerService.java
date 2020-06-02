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

    public boolean checkForDuplicateUserName(String user_name) {
        List<String> UserNameList = customerRepo.fetchUserNameFromCustomer();
        if (UserNameList.contains(user_name)){
            return true;
        }
        return false;
    }

    public boolean checkForDuplicateEmail(String email) {
        List<String> emailList = customerRepo.fetchEmailFromCustomer();
        if (emailList.contains(email)){
            return true;
        }
        return false;
    }
    public boolean checkForDuplicatePhoneNumber(String phonenumber) {
        List<String> phoneNumberList = customerRepo.fetchPhoneNumberFromCustomer();
        if (phoneNumberList.contains(phonenumber)){
            return true;
        }
        return false;
    }
}
