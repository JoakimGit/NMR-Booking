package com.example.demo.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

//Lavet af Emil
class EmployeeServiceTest {

    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeService();
    }

    @ParameterizedTest
    @CsvSource(value={"235622-11:23562211", "23-56-22-11:23562211"}, delimiter =':')
    void formatPhone(String input, String expected) {
        String actualValue = employeeService.formatPhone(input);
        assertEquals(expected, actualValue);
    }

    @Test
    void formatCpr() {
        String cpr = employeeService.formatCpr("2356221122");
        String expResult = "235622-1122";
        assertEquals(cpr, expResult);
    }
}