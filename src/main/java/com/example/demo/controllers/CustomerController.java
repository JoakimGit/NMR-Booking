package com.example.demo.controllers;

import com.example.demo.exceptions.DuplicateExceptionEmail;
import com.example.demo.exceptions.DuplicateExceptionPhoneNumber;
import com.example.demo.exceptions.DuplicateExceptionUserName;
import com.example.demo.models.Customer;
import com.example.demo.services.AccessoryService;
import com.example.demo.services.CustomerService;
import com.example.demo.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    AccessoryService accessoryService;

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
    public String createCustomer(@ModelAttribute Customer customer) throws DuplicateExceptionUserName,DuplicateExceptionEmail,DuplicateExceptionPhoneNumber  {
        boolean UserNameExist = customerService.checkForDuplicateUserName(customer.getUser_name());
        boolean emailExist = customerService.checkForDuplicateEmail(customer.getEmail());
        boolean phoneNumberExit = customerService.checkForDuplicatePhoneNumber(customer.getPhonenumber());
        if (UserNameExist){
            throw new DuplicateExceptionUserName("Du får vist denne side fordi en fejl er opstået");
        }
        if (emailExist){
            throw new DuplicateExceptionEmail("Du får vist denne side fordi en fejl er opstået");
        }
        if (phoneNumberExit){
            throw new DuplicateExceptionPhoneNumber("Du får vist denne side fordi en fejl er opstået");
        }
        customerService.createCustomer(customer);
        return "redirect:/kunde/oversigt";
    }

    @GetMapping("/kunde/rediger/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("customer", customerService.fetchCustomerById(id));
        return "/customer/edit";
    }

    @PostMapping("/kunde/rediger")
    public String editCustomer(@ModelAttribute Customer customer)throws DuplicateExceptionUserName,DuplicateExceptionEmail, DuplicateExceptionPhoneNumber {
        boolean UserNameExist = customerService.checkForDuplicateUserName(customer.getUser_name());
        boolean emailExist = customerService.checkForDuplicateEmail(customer.getEmail());
        boolean phoneNumberExit = customerService.checkForDuplicatePhoneNumber(customer.getPhonenumber());
        if (UserNameExist){
            throw new DuplicateExceptionUserName("Du får vist denne side fordi en fejl er opstået");
        }
        if (emailExist){
            throw new DuplicateExceptionEmail("Du får vist denne side fordi en fejl er opstået");
        }
        if (phoneNumberExit){
            throw new DuplicateExceptionPhoneNumber("Du får vist denne side fordi en fejl er opstået");
        }
        customerService.editCustomer(customer);
        return "redirect:/kunde/oversigt";
    }

    @GetMapping("/kunde/slet/{id}")
    public String delete(@PathVariable("id") int id) {
        customerService.deleteCustomer(id);
        return "redirect:/kunde/oversigt";
    }

    @GetMapping("/kunde/reservationer/{user_name}")
    public String customerReservationOverview(@PathVariable("user_name") String user_name, Model model) {
        model.addAttribute("reservations", reservationService.fetchReservationsByCustomerUsername(user_name));
        return "/customer/reservations";
    }

    @ExceptionHandler(DuplicateExceptionUserName.class)
    public String databaseError(Model model, DuplicateExceptionUserName exception) {
        model.addAttribute("besked",exception.getMessage());
        model.addAttribute("tilbage","/kunde/opret");
        return "/error/duplicate-exception-username";
    }

    @ExceptionHandler(DuplicateExceptionEmail.class)
    public String databaseError(Model model, DuplicateExceptionEmail exception) {
        model.addAttribute("besked",exception.getMessage());
        model.addAttribute("tilbage","/kunde/opret");
        return "/error/duplicate-exception-email";
    }

    @ExceptionHandler(DuplicateExceptionPhoneNumber.class)
    public String databaseError(Model model, DuplicateExceptionPhoneNumber exception) {
        model.addAttribute("besked", exception.getMessage());
        model.addAttribute("tilbage","/kunde/opret");
        return "/error/duplicate-exception-phonenumber";
    }
}
