package com.example.demo.exceptions;

import java.sql.SQLException;

//Lavet af Daniel
public class DuplicateExceptionEmail extends SQLException {
    public DuplicateExceptionEmail(String message){
        super(message);
    }
}