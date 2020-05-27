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

    private List<String> allBrandModels = Arrays.asList("Fiat - MCLouis 2,3 MC4-75 3d", "Hymer - Camp Classic 644 2,8 JTD", "Bürstner - Lyseo Time 734", "Hobby - Optima T75 HE 2,3 Premium", "Elnagh - T-Loft 531", "Globe-Traveller - Pathfinder Z", "Rapido - 891 F", "Frankia - A650");
    private List<String> allSeasons = Arrays.asList("Højsæson", "Midtsæson", "Lavsæson");

    @Autowired
    ReservationService reservationService;

    @Autowired
    AccessoryService accessoryService;

    @GetMapping("/reservation/overview")
    public String overview(Model model) {
        model.addAttribute("reservations", reservationService.fetchAllReservations());
        model.addAttribute("accessories", accessoryService.fetchAllAccessoryNames());
        return "/reservation/overview";
    }

    @GetMapping("/reservation/create")
    public String create(Model model) {
        model.addAttribute("brand_model", allBrandModels);
        model.addAttribute("season", allSeasons);
        model.addAttribute("accessories", accessoryService.fetchAllAccessoryNames());
        return "/reservation/create";
    }

    @PostMapping("/reservation/create")
    public String createReservation(@ModelAttribute Reservation reservation, @ModelAttribute Accessory accessory) {
        reservationService.createReservation(reservation, accessory);
        return "redirect:/reservation/overview";
    }

    @GetMapping("/reservation/edit/{id}")
    public String update(@PathVariable("id") int id, Model model) {
        model.addAttribute("reservation", reservationService.fetchReservationById(id));
        model.addAttribute("brand_model", allBrandModels);
        model.addAttribute("season", allSeasons);
        model.addAttribute("accessories", accessoryService.fetchAllAccessoryNames());
        model.addAttribute("chosenAccessories", accessoryService.fetchAllChosenAccessoriesById(id));
        return "/reservation/edit";
    }

    @PostMapping("reservation/edit")
    public String editReservation(@ModelAttribute Reservation reservation, @ModelAttribute Accessory accessory) {
        reservationService.editReservation(reservation, accessory);
        return "redirect:/reservation/overview";
    }

    @GetMapping("reservation/delete/{id}")
    public String deleteReservation(@PathVariable("id") int id) {
        reservationService.deleteReservation(id);
        return "redirect:/reservation/overview";
    }
}
