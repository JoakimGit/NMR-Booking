package com.example.demo.services;

import com.example.demo.models.Invoice;
import com.example.demo.models.Motorhome;
import com.example.demo.models.Reservation;
import com.example.demo.repositories.InvoiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    InvoiceRepo invoiceRepo;

    @Autowired
    MotorhomeService motorhomeService;

    @Autowired
    ReservationService reservationService;

    public List<Invoice> fetchAllInvoices() {
        return invoiceRepo.fetchAllInvoices();
    }

    public Invoice fetchInvoiceById(int id) {
        return invoiceRepo.fetchInvoiceById(id);
    }

    public Invoice fetchInvoiceByReservationId(int id) {
        return invoiceRepo.fetchInvoiceByReservationId(id);
    }

    public void createInvoice(Invoice invoice) {
        invoiceRepo.createInvoice(invoice);
    }

    public void createInvoiceFromReservation(Reservation reservation, int reservation_id) {
        Invoice invoice = new Invoice();
        invoice.setReservation_id(reservation_id);
        invoice.setInvoice_type("Reservation Pris");

        calcInvoiceTotal(reservation, invoice);
        invoiceRepo.createInvoice(invoice);
    }

    public void updateInvoiceFromReservation(Reservation reservation) {
        Invoice invoice = fetchInvoiceByReservationId(reservation.getReservation_id());
        invoice.setReservation_id(reservation.getReservation_id());

        calcInvoiceTotal(reservation, invoice);
        invoiceRepo.updateInvoice(invoice);
    }

    public void updateInvoice(Invoice invoice) {
        invoiceRepo.updateInvoice(invoice);
    }

    public void deleteInvoice(int id) {
        invoiceRepo.deleteInvoice(id);
    }

    public void createCancellationInvoice(Reservation reservation, Invoice invoice) {
        Invoice cancellationInvoice = new Invoice();
        String currentdate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String reservationstart = reservation.getPickup_date();
        int daysBetween = calcDaysBetweenDates(currentdate, reservationstart);
        double reservationprice = invoice.getTotal();
        if (daysBetween > 50) {
            reservationprice = reservationprice*0.8;
        }
        else if (daysBetween < 50 && daysBetween >= 15) {
            reservationprice = reservationprice*0.5;
        }
        else if (daysBetween < 15 && daysBetween >= 1) {
            reservationprice = reservationprice*0.8;
        }
        else if (daysBetween == 0) {
            reservationprice = reservationprice*0.95;
        }
        cancellationInvoice.setInvoice_type("Afmeldingsgebyr");
        cancellationInvoice.setTotal(reservationprice);
        cancellationInvoice.setReservation_id(reservation.getReservation_id());
        createInvoice(cancellationInvoice);
        motorhomeService.setMotorhomeAvailable(reservation.getLicense_plate());
        reservationService.setReservationFinished(reservation.getLicense_plate());
    }

    public void calcInvoiceTotal(Reservation reservation, Invoice invoice) {
        Motorhome motorhome = motorhomeService.fetchMotorhomeByLicense(reservation.getLicense_plate());
        double priceperday = motorhome.getPrice();

        String date1 = reservation.getPickup_date();
        String date2 = reservation.getDropoff_date();
        int daysBetween = calcDaysBetweenDates(date1, date2);

        double price = daysBetween*priceperday;

        if (reservation.getSeason().equals("Højsæson")) {
            price *= 1.6;
        }
        else if (reservation.getSeason().equals("Midtsæson")) {
            price *= 1.3;
        }
        invoice.setTotal(price);
    }

    public int calcDaysBetweenDates(String first_date, String second_date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate date1 = LocalDate.parse(first_date, dtf);
            LocalDate date2 = LocalDate.parse(second_date, dtf);
            return (int) Duration.between(date1.atStartOfDay(), date2.atStartOfDay()).toDays();
        } catch (DateTimeParseException e) {
            System.out.println("Husk at skrive datoen ind i den rigtige format yyyy-MM-dd");
            e.printStackTrace();
        }
        return 0;
    }
}
