package com.example.demo.exceptions;

import java.sql.SQLException;

//Lavet af Daniel
public class DuplicateExceptionUserName extends SQLException {
    public DuplicateExceptionUserName(String message){
        super(message);
    }
}