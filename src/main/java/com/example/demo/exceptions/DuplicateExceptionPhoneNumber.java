package com.example.demo.exceptions;

import java.sql.SQLException;

//Lavet af Daniel
public class DuplicateExceptionPhoneNumber extends SQLException {
    public DuplicateExceptionPhoneNumber(String message){
        super(message);
    }
}