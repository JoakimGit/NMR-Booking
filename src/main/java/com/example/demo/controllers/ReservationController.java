package com.example.demo.controllers;

import com.example.demo.models.Invoice;
import com.example.demo.models.Motorhome;
import com.example.demo.models.Reservation;
import com.example.demo.services.AccessoryService;
import com.example.demo.services.InvoiceService;
import com.example.demo.services.MotorhomeService;
import com.example.demo.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

//Lavet af Magnus og Joakim
@Controller
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    AccessoryService accessoryService;

    @Autowired
    MotorhomeService motorhomeService;

    @Autowired
    InvoiceService invoiceService;

    @GetMapping("/reservation/oversigt")
    public String overview(Model model) {
        model.addAttribute("reservations", reservationService.fetchAllReservations());
        model.addAttribute("resService", new ReservationService());
        model.addAttribute("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        return "/reservation/overview";
    }

    @GetMapping("/reservation/opret")
    public String create(Model model) {
        model.addAttribute("motorhomes", motorhomeService.fetchAllAvailableMotorhomes());
        model.addAttribute("accessories", accessoryService.fetchAllAccessoryNames());
        return "/reservation/create";
    }

    @PostMapping("/reservation/opret")
    public String createReservation(@ModelAttribute Reservation reservation, Model model) {
        if (!(reservationService.compareDates(reservation.getPickup_date(), reservation.getDropoff_date()))) {
            model.addAttribute("reservation", reservation);
            model.addAttribute("tilbage", "/reservation/opret");
            return "/error/startdate-after-enddate";
        }
        reservationService.createReservation(reservation);
        return "redirect:/reservation/oversigt";
    }

    @GetMapping("/reservation/rediger/{id}/{license}")
    public String update(@PathVariable("id") int id, @PathVariable("license") String license, Model model) {
        Motorhome motorhome = motorhomeService.fetchMotorhomeByLicense(license);
        model.addAttribute("currentbrandandmodel", motorhome.getBrand() + " - " + motorhome.getModel());
        model.addAttribute("motorhomes", motorhomeService.fetchAllOtherAvailableMotorhomes(motorhome.getModel()));
        model.addAttribute("reservation", reservationService.fetchReservationById(id));
        model.addAttribute("accessories", accessoryService.fetchAllAccessoryNames());
        model.addAttribute("chosenAccessories", accessoryService.fetchAllChosenAccessoriesById(id));
        return "/reservation/edit";
    }

    @PostMapping("reservation/rediger")
    public String editReservation(@ModelAttribute Reservation reservation, WebRequest param, Model model) {
        if (!(reservationService.compareDates(reservation.getPickup_date(), reservation.getDropoff_date()))) {
            model.addAttribute("reservation", reservation);
            model.addAttribute("tilbage", "/reservation/rediger/"+reservation.getReservation_id()+"/"+reservation.getLicense_plate());
            return "/error/startdate-after-enddate";
        }
        reservationService.editReservation(reservation, param.getParameter("newlicense"));
        return "redirect:/reservation/oversigt";
    }

    @GetMapping("reservation/slet/{id}")
    public String deleteReservation(@PathVariable("id") int id) {
        reservationService.deleteReservation(id);
        return "redirect:/reservation/oversigt";
    }

    @GetMapping("/reservation/afslut/{license}")
    public String finishReservation(@PathVariable("license") String license, Model model) {
        motorhomeService.setMotorhomeAvailable(license);
        reservationService.setReservationFinished(license);
        model.addAttribute("motorhome", motorhomeService.fetchMotorhomeByLicense(license));
        return "/reservation/closed-reservation";
    }

    @GetMapping("/reservation/afbestil/{id}")
    public String cancelReservation(@PathVariable("id") int id) {
        Reservation reservation = reservationService.fetchReservationById(id);
        Invoice invoice = invoiceService.fetchInvoiceByReservationId(id);
        invoiceService.createCancellationInvoice(reservation, invoice);
        return "redirect:/reservation/oversigt";
    }
}
