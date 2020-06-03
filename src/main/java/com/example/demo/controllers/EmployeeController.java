package com.example.demo.controllers;

import com.example.demo.exceptions.DuplicateExceptionCpr;
import com.example.demo.exceptions.DuplicateExceptionEmail;
import com.example.demo.exceptions.DuplicateExceptionPhoneNumber;
import com.example.demo.models.Employee;
import com.example.demo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

//Lavet af Joakim
@Controller
public class EmployeeController {

    private final List<String> allJobs = Arrays.asList("Ejer", "Salgs Leder", "Salgs Assistent", "Bogfører", "Rengøringspersonale", "Mekaniker");

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/medarbejder/oversigt")
    public String showEmployee(Model model) {
        model.addAttribute("employees", employeeService.fetchAllEmployees());
        return "/employee/overview";
    }

    @GetMapping("/medarbejder/opret")
    public String create(Model model) {
        model.addAttribute("jobs", allJobs);
        return "/employee/create";
    }

    @PostMapping("/medarbejder/opret")
    public String createEmployee(@ModelAttribute Employee employee) throws DuplicateExceptionCpr, DuplicateExceptionEmail, DuplicateExceptionPhoneNumber {
        boolean cprExist = employeeService.checkForDuplicateCpr(employee.getCpr());
        boolean emailExist = employeeService.checkForDuplicateEmail(employee.getEmail());
        boolean phoneNumberExit = employeeService.checkForDuplicatePhoneNumber(employee.getPhonenumber());
        if (cprExist){
            throw new DuplicateExceptionCpr("Du får vist denne side fordi en fejl er opstået");
        }
        if(emailExist){
            throw new DuplicateExceptionEmail("Du får vist denne side fordi en fejl er opstået");
        }
        if (phoneNumberExit){
            throw new DuplicateExceptionPhoneNumber("Du får vist denne side fordi en fejl er opstået");
        }
        employeeService.createEmployee(employee);
        return "redirect:/medarbejder/oversigt";
    }

    @GetMapping("medarbejder/rediger/{id}")
    public String update(@PathVariable("id") int id, Model model) {
        model.addAttribute("employee", employeeService.fetchEmployeeById(id));
        model.addAttribute("jobs", allJobs);
        return "/employee/edit";
    }

    @PostMapping("/medarbejder/rediger")
    public String updateEmployee(@ModelAttribute Employee employee) throws DuplicateExceptionCpr, DuplicateExceptionEmail, DuplicateExceptionPhoneNumber {
        boolean cprExist = employeeService.checkForOtherDuplicateCpr(employee.getCpr(), employee.getEmployee_id());
        boolean emailExist = employeeService.checkForOtherDuplicateEmail(employee.getEmail(), employee.getEmployee_id());
        boolean phoneNumberExit = employeeService.checkForOtherDuplicatePhoneNumber(employee.getPhonenumber(), employee.getEmployee_id());
        if (cprExist){
            throw new DuplicateExceptionCpr("/kunde/rediger/"+employee.getEmployee_id());
        }
        if(emailExist){
            throw new DuplicateExceptionEmail("/kunde/rediger/"+employee.getEmployee_id());
        }
        if (phoneNumberExit){
            throw new DuplicateExceptionPhoneNumber("/kunde/rediger/"+employee.getEmployee_id());
        }
        employeeService.updateEmployee(employee);
        return "redirect:/medarbejder/oversigt";
    }

    @GetMapping("medarbejder/slet/{id}")
    public String deleteEmployee(@PathVariable("id") int id) {
        employeeService.deleteEmployee(id);
        return "redirect:/medarbejder/oversigt";
    }

    @ExceptionHandler(DuplicateExceptionCpr.class)
    public String databaseError(Model model, DuplicateExceptionCpr exception) {
        model.addAttribute("tilbage",exception.getMessage());
        return "/error/duplicate-exception-cpr";
    }

    @ExceptionHandler(DuplicateExceptionEmail.class)
    public String databaseError(Model model, DuplicateExceptionEmail exception) {
        model.addAttribute("tilbage",exception.getMessage());
        return "/error/duplicate-exception-email";
    }

    @ExceptionHandler(DuplicateExceptionPhoneNumber.class)
    public String databaseError(Model model, DuplicateExceptionPhoneNumber exception) {
        model.addAttribute("tilbage", exception.getMessage());
        return "/error/duplicate-exception-phonenumber";
    }
}
