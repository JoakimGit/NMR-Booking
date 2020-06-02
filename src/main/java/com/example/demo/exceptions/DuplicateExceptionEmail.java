package com.example.demo.exceptions;

import java.sql.SQLException;

public class DuplicateExceptionEmail extends SQLException {
    public DuplicateExceptionEmail(String message){
        super(message);
    }
}