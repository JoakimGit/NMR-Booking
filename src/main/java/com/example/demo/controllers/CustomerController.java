package com.example.demo.controllers;

import com.example.demo.models.Customer;
import com.example.demo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/customer/overview")
    public String overview(Model model) {
        model.addAttribute("customers", customerService.fetchAllCustomers());
        return "/customer/overview";
    }

    @GetMapping("/customer/create")
    public String create() {
        return "/customer/create";
    }

    @PostMapping("/customer/create")
    public String createCustomer(@ModelAttribute Customer customer) {
        customerService.createCustomer(customer);
        return "redirect:/customer/overview";
    }

    @GetMapping("/customer/edit/{customer_id}")
    public String edit(@PathVariable("customer_id") int customer_id, Model model) {
        model.addAttribute("customer", customerService.fetchCustomerById(customer_id));
        return "/customer/edit";
    }

    @PostMapping("/customer/edit")
    public String editCustomer(@ModelAttribute Customer customer) {
        customerService.editCustomer(customer);
        return "redirect:/customer/overview";
    }

    @GetMapping("/customer/delete/{customer_id}")
    public String delete(@PathVariable("customer_id") int customer_id) {
        customerService.deleteCustomer(customer_id);
        return "redirect:/customer/overview";
    }

}
