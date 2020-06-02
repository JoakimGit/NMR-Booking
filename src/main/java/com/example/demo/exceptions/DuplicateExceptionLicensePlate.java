package com.example.demo.exceptions;

import java.sql.SQLException;

public class DuplicateExceptionLicensePlate extends SQLException {
    public DuplicateExceptionLicensePlate(String message){
        super(message);
    }
}