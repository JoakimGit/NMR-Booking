package com.example.demo.exceptions;

import java.sql.SQLException;

//Lavet af Daniel
public class DuplicateExceptionCpr extends SQLException {
    public DuplicateExceptionCpr(String message){
        super(message);
    }
}
