package com.example.demo.exceptions;

import java.sql.SQLException;

public class DuplicateExceptionCpr extends SQLException {
    public DuplicateExceptionCpr(String message){
        super(message);
    }
}
