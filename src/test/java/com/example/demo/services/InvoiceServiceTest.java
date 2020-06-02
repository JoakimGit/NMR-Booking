package com.example.demo.services;

import com.example.demo.models.Invoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceServiceTest {

    private InvoiceService invoiceService;

    @BeforeEach
    void setUp() {
        invoiceService = new InvoiceService();
    }

    @Test
    void calcDaysBetweenDatesValid() {
            int calc = invoiceService.calcDaysBetweenDates("2015-12-12", "2015-12-20");
            int expResult = 8;
            assertEquals(expResult, calc);
    }

    //Denne test ville virke hvis vi ikke allerede havde en try catch i metoden, men bare throwede exceptionen
    @Test
    void calcDaysBetweenDatesInvalid() {
        try {
            invoiceService.calcDaysBetweenDates("201-12-12", "2015-12-20");
        }
        catch(DateTimeParseException e) {
            System.out.println("Properly caught IllegalArgumentException"+e.getMessage());
        }
        catch(Exception e) {
            fail("Wrong exception thrown");
        }
    }
}