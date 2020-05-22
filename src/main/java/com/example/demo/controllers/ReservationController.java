package com.example.demo.controllers;

import com.example.demo.models.Accessory;
import com.example.demo.models.Reservation;
import com.example.demo.services.AccessoryService;
import com.example.demo.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    AccessoryService accessoryService;

    private List<String> allAccessories= Arrays.asList("Cykelstativ", "Sengelinned", "Barnesæde", "Picnic bord", "Campingstol");

    @GetMapping("/reservation/overview")
    public String overview(Model model) {
        model.addAttribute("reservations", reservationService.fetchAllReservations());
        return "/reservation/overview";
    }

    @GetMapping("/reservation/create")
    public String create(Model model) {
        model.addAttribute("accessories", allAccessories);
        return "/reservation/create";
    }

    @PostMapping("/reservation/create")
    public String createReservation(@ModelAttribute Reservation reservation, @ModelAttribute Accessory accessory) {
        accessoryService.createAccessory(accessory);
        reservationService.createReservation(reservation);
        return "redirect:/reservation/overview";
    }

    @GetMapping("/reservation/edit/{id}")
    public String update(@PathVariable("id") int id, Model model) {
        model.addAttribute("reservation", reservationService.findReservationById(id));
        model.addAttribute("accessories", allAccessories);
        return "/reservation/edit";
    }

    @PostMapping("reservation/edit")
    public String editReservation(@ModelAttribute Reservation reservation) {
    reservationService.editReservation(reservation);
    return "redirect:/reservation/overview";
    }

    @GetMapping("reservation/delete/{id}")
    public String deleteReservation(@PathVariable("id") int id) {
    reservationService.deleteReservation(id);
    return "redirect:/reservation/overview";

    }
}
