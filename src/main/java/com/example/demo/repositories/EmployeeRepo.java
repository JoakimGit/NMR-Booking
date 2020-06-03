package com.example.demo.repositories;

import com.example.demo.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//Lavet af Joakim
@Repository
public class EmployeeRepo {

    @Autowired
    JdbcTemplate template;

    public Employee fetchEmployeeById(int id) {
        String sql = "SELECT * FROM employee WHERE employee_id = ?";
        RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
        return template.queryForObject(sql, rowMapper, id);
    }

    public List<Employee> fetchAllEmployees() {
        String sql = "SELECT * FROM employee";
        RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
        return template.query(sql, rowMapper);
    }

    public void createEmployee(Employee e) {
        String sql = "INSERT INTO employee VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, e.getEmployee_id(), e.getFirst_name(), e.getLast_name(), e.getCpr(), e.getJob_title(), e.getEmail(), e.getPhonenumber(), e.getEmployment_date());
    }

    public void updateEmployee(Employee e) {
        String sql = "UPDATE employee SET first_name=?, last_name=?, cpr=?, job_title=?, email=?, phonenumber=?, employment_date=? WHERE employee_id=?";
        template.update(sql, e.getFirst_name(), e.getLast_name(), e.getCpr(), e.getJob_title(), e.getEmail() ,e.getPhonenumber(), e.getEmployment_date(), e.getEmployee_id());
    }

    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employee WHERE employee_id = ?";
        template.update(sql, id);
    }

    // Methods used for checking for duplicates
    public List<String> fetchCprFromEmployee() {
        String sql = "SELECT cpr FROM employee";
        return template.queryForList(sql, String.class);
    }

    public List<String> fetchEmailFromEmployee() {
        String sql = "SELECT email FROM employee";
        return template.queryForList(sql, String.class);
    }

    public List<String> fetchPhoneNumberFromEmployee() {
        String sql = "SELECT phonenumber FROM employee";
        return template.queryForList(sql, String.class);
    }

    public List<String> fetchCprFromOtherEmployee(int id) {
        String sql = "SELECT cpr FROM employee WHERE employee_id!=?";
        return template.queryForList(sql, String.class, id);
    }

    public List<String> fetchEmailFromOtherEmployee(int id) {
        String sql = "SELECT email FROM employee WHERE employee_id!=?";
        return template.queryForList(sql, String.class, id);
    }

    public List<String> fetchPhoneNumberFromOtherEmployee(int id) {
        String sql = "SELECT phonenumber FROM employee WHERE employee_id!=?";
        return template.queryForList(sql, String.class, id);
    }
}