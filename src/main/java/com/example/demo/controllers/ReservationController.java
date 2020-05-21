package com.example.demo.controllers;

import com.example.demo.models.Reservation;
import com.example.demo.repositories.IReservationRepo;
import com.example.demo.repositories.ReservationRepo;
import com.example.demo.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @GetMapping("/reservation/overview")
    public String overview(Model model) {
    model.addAttribute("reservations", reservationService.fetchAllReservations());
    return "/reservation/overview";
    }

    @GetMapping("/reservation/create")
    public String create() {
    return "/reservation/create";
    }

    @PostMapping("/reservation/create")
    public String createReservation(@ModelAttribute Reservation reservation) {
    reservationService.addReservation(reservation);
    return "redirect:/reservation/overview";
    }

    @GetMapping("/reservation/edit/{id}")
    public String update(@PathVariable("id") int id, Model model) {
    model.addAttribute("reservation", reservationService.findReservationById(id));
    return "/reservation/edit";
    }

    @PostMapping("reservation/edit")
    public String updateReservation(@ModelAttribute Reservation reservation) {
    reservationService.updateReservation(reservation);
    return "redirect:/reservation/overview";
    }

    @GetMapping("reservation/delete/{id}")
    public String deleteReservation(@PathVariable("id") int id) {
    reservationService.deleteReservation(id);
    return "redirect:/reservation/overview";

    }
}
