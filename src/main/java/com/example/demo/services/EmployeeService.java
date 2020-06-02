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
        e.setPhonenumber(formatPhone(e.getPhonenumber()));
        e.setCpr(formatCpr(e.getCpr()));
        employeeRepo.createEmployee(e);

        User user = new User();
        user.setUsername(e.getFirst_name());
        user.setPassword("123" + e.getLast_name());
        user.setRole(e.getAuthority());
        userService.createUser(user);
    }

    public void updateEmployee(Employee e) {
        e.setPhonenumber(formatPhone(e.getPhonenumber()));
        e.setCpr(formatCpr(e.getCpr()));
        employeeRepo.updateEmployee(e);
    }

    public void deleteEmployee(int id) {
        employeeRepo.deleteEmployee(id);
    }

    public String formatPhone(String phone) {
        if (phone.length() > 8) {
            phone = phone.replaceAll("[\\-]", "");
        }
        return phone;
    }

    public String formatCpr(String cpr) {
        if (cpr.length() == 10) {
            cpr = cpr.substring(0, 6) + "-" + cpr.substring(6);
        }
        return cpr;
    }

    public boolean checkForDuplicateCpr(String cpr) {
        List<String> cprList = employeeRepo.fetchCprFromEmployee();
        if (cprList.contains(cpr)){
            return true;
        }
        return false;
    }

    public boolean checkForDuplicateEmail(String email) {
        List<String> emailList = employeeRepo.fetchEmailFromEmployee();
        if (emailList.contains(email)){
            return true;
        }
        return false;
    }
    public boolean checkForDuplicatePhoneNumber(String phonenumber) {
        List<String> phoneNumberList = employeeRepo.fetchPhoneNumberFromEmployee();
        if (phoneNumberList.contains(phonenumber)){
            return true;
        }
        return false;
    }

}
