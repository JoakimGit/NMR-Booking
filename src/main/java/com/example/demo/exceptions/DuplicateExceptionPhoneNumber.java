package com.example.demo.exceptions;

import java.sql.SQLException;

public class DuplicateExceptionPhoneNumber extends SQLException {
    public DuplicateExceptionPhoneNumber(String message){
        super(message);
    }
}