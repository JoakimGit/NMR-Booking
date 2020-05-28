package com.example.demo.services;

import com.example.demo.models.Employee;
import com.example.demo.models.User;
import com.example.demo.repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    UserService userService;

    public Employee fetchEmployeeById(int id) {
        return employeeRepo.fetchEmployeeById(id);
    }

    public List<Employee> fetchAllEmployees() {
        return employeeRepo.fetchAllEmployees();
    }

    public void createEmployee(Employee e) {
        formatPhone(e.getPhonenumber(), e);
        formatCpr(e.getCpr(), e);
        employeeRepo.createEmployee(e);

        User user = new User();
        user.setUsername(e.getFirst_name());
        user.setPassword("123" + e.getLast_name());
        user.setRole(e.getAuthority());
        userService.createUser(user);
    }

    public void updateEmployee(Employee e) {
        formatPhone(e.getPhonenumber(), e);
        formatCpr(e.getCpr(), e);
        employeeRepo.updateEmployee(e);
    }

    public void deleteEmployee(int id) {
        employeeRepo.deleteEmployee(id);
    }

    public void formatPhone(String phone, Employee employee) {
        if (phone.length() > 8) {
            phone = phone.replaceAll("[\\-]", "");
            employee.setPhonenumber(phone);
        }
    }

    public void formatCpr(String cpr, Employee employee) {
        if (cpr.length() == 10) {
            cpr = cpr.substring(0, 6) + "-" + cpr.substring(6);
            employee.setCpr(cpr);
        }
    }
}
