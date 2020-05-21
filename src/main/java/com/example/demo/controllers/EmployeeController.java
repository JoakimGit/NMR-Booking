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

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employee/overview")
    public String showEmployee(Model model) {
        model.addAttribute("employees", employeeService.fetchAllEmployees());
        return "/employee/overview";
    }

    @GetMapping("employee/detail/{id}")
    public String getStudentByParameter(@PathVariable("id") int id, Model model) {
        model.addAttribute("employee", employeeService.fetchEmployeeById(id));
        return "employee/detail";
    }

    @GetMapping("/employee/create")
    public String create() {
        return "/employee/create";
    }

    @PostMapping("/employee/create")
    public String createStudent(@ModelAttribute Employee employee) {
        employeeService.createEmployee(employee);
        return "redirect:/employee/overview";
    }

    @GetMapping("employee/edit/{id}")
    public String update(@PathVariable("id") int id, Model model) {
        model.addAttribute("employee", employeeService.fetchEmployeeById(id));
        return "/employee/edit";
    }

    @PostMapping("/employee/edit")
    public String updateEmployee(@ModelAttribute Employee employee) {
        employeeService.updateEmployee(employee);
        return "redirect:/employee/overview";
    }

    @GetMapping("employee/delete/{id}")
    public String deleteStudent(@PathVariable("id") int id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employee/overview";
    }
}
