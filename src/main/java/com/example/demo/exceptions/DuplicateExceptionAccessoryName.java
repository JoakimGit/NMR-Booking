package com.example.demo.exceptions;

import java.sql.SQLException;

//Lavet af Daniel
public class DuplicateExceptionAccessoryName extends SQLException {
    public DuplicateExceptionAccessoryName(String message){
        super(message);
    }
}
