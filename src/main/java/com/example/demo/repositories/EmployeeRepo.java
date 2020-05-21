package com.example.demo.repositories;

import com.example.demo.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        String sql = "INSERT INTO employee VALUES (?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, e.getEmployee_id(), e.getFirst_name(), e.getLast_name(), e.getCpr(), e.getRole(), e.getPhonenumber(), e.getEmployment_date());
    }

    public void updateEmployee(Employee e) {
        String sql = "UPDATE employee SET first_name=?, last_name=?, cpr=?, role=?, phonenumber=?, employment_date=? WHERE employee_id=?";
        template.update(sql, e.getFirst_name(), e.getLast_name(), e.getCpr(), e.getRole(), e.getPhonenumber(), e.getEmployment_date(), e.getEmployee_id());
    }

    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employee WHERE employee_id = ?";
        template.update(sql, id);
    }



}
