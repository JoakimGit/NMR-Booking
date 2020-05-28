package com.example.demo.controllers;

import com.example.demo.models.Reservation;
import com.example.demo.services.AccessoryService;
import com.example.demo.services.MotorhomeService;
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

    @Autowired
    AccessoryService accessoryService;

    @Autowired
    MotorhomeService motorhomeService;

    @GetMapping("/reservation/oversigt")
    public String overview(Model model) {
        model.addAttribute("reservations", reservationService.fetchAllReservations());
        return "/reservation/overview";
    }

    @GetMapping("/reservation/opret")
    public String create(Model model) {
        model.addAttribute("brand_model", motorhomeService.fetchMotorhomeBrandAndModel());
        model.addAttribute("accessories", accessoryService.fetchAllAccessoryNames());
        return "/reservation/create";
    }

    @PostMapping("/reservation/opret")
    public String createReservation(@ModelAttribute Reservation reservation) {
        reservationService.createReservation(reservation);
        return "redirect:/reservation/overview";
    }

    @GetMapping("/reservation/rediger/{id}")
    public String update(@PathVariable("id") int id, Model model) {
        model.addAttribute("reservation", reservationService.fetchReservationById(id));
        model.addAttribute("brand_model", motorhomeService.fetchMotorhomeBrandAndModel());
        model.addAttribute("accessories", accessoryService.fetchAllAccessoryNames());
        model.addAttribute("chosenAccessories", accessoryService.fetchAllChosenAccessoriesById(id));
        return "/reservation/edit";
    }

    @PostMapping("reservation/rediger")
    public String editReservation(@ModelAttribute Reservation reservation) {
        reservationService.editReservation(reservation);
        return "redirect:/reservation/overview";
    }

    @GetMapping("reservation/slet/{id}")
    public String deleteReservation(@PathVariable("id") int id) {
        reservationService.deleteReservation(id);
        return "redirect:/reservation/overview";
    }
}
