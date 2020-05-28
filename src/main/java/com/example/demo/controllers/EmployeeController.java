package com.example.demo.controllers;

import com.example.demo.models.Employee;
import com.example.demo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class EmployeeController {

    private List<String> allJobs = Arrays.asList("Salgs Assistent", "Salgs Leder", "Rengøringspersonale", "Bogfører", "Mekaniker");

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

    @GetMapping("medarbejder/rediger/{id}")
    public String update(@PathVariable("id") int id, Model model) {
        model.addAttribute("employee", employeeService.fetchEmployeeById(id));
        model.addAttribute("jobs", allJobs);
        return "/employee/edit";
    }

    @PostMapping("/medarbejder/rediger")
    public String updateEmployee(@ModelAttribute Employee employee) {
        employeeService.updateEmployee(employee);
        return "redirect:/employee/overview";
    }

    @GetMapping("medarbejder/slet/{id}")
    public String deleteEmployee(@PathVariable("id") int id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employee/overview";
    }
}
