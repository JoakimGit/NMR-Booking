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

    @GetMapping("/kunde/oversigt")
    public String overview(Model model) {
        model.addAttribute("customers", customerService.fetchAllCustomers());
        return "/customer/overview";
    }

    @GetMapping("/kunde/opret")
    public String create() {
        return "/customer/create";
    }

    @PostMapping("/kunde/opret")
    public String createCustomer(@ModelAttribute Customer customer) {
        customerService.createCustomer(customer);
        return "redirect:/kunde/oversigt";
    }

    @GetMapping("/kunde/rediger/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("customer", customerService.fetchCustomerById(id));
        return "/customer/edit";
    }

    @PostMapping("/kunde/rediger")
    public String editCustomer(@ModelAttribute Customer customer) {
        customerService.editCustomer(customer);
        return "redirect:/kunde/oversigt";
    }

    @GetMapping("/kunde/slet/{id}")
    public String delete(@PathVariable("id") int id) {
        customerService.deleteCustomer(id);
        return "redirect:/kunde/oversigt";
    }

}
