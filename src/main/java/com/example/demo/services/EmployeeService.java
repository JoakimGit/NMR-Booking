package com.example.demo.services;

import com.example.demo.models.Employee;
import com.example.demo.repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    public Employee fetchEmployeeById(int id) {
        return employeeRepo.fetchEmployeeById(id);
    }

    public List<Employee> fetchAllEmployees() {
        return employeeRepo.fetchAllEmployees();
    }

    public void createEmployee(Employee e) {
        employeeRepo.createEmployee(e);
    }

    public void updateEmployee(Employee e) {
        employeeRepo.updateEmployee(e);
    }

    public void deleteEmployee(int id) {
        employeeRepo.deleteEmployee(id);
    }


}
