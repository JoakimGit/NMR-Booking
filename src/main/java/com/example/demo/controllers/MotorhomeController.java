package com.example.demo.controllers;

import com.example.demo.models.Motorhome;

import com.example.demo.services.MotorhomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MotorhomeController {

    @Autowired
    MotorhomeService motorhomeService;

    @GetMapping("/motorhome/overview")
    public String overview(Model model){
        model.addAttribute("motorhomes", motorhomeService.fetchAllDistinctMotorhomes());
        return "/motorhome/overview";
    }

    @GetMapping("/motorhome/edit/{motorhome_id}")
    public String findMotorhome(@PathVariable("motorhome_id")int motorhome_id, Model model){
        model.addAttribute("motorhome",motorhomeService.fetchMotorhomeByID(motorhome_id));
        return "/motorhome/edit";
    }

    @PostMapping("/motorhome/edit")
    public String Update(@ModelAttribute Motorhome motorhome){
        motorhomeService.updateMotorhome(motorhome);
        return "redirect:/motorhome/overview";
    }

    @GetMapping("/motorhome/create")
    public String showCreateMotorhomePage(){
        return "/motorhome/create";
    }

    @PostMapping("/motorhome/create")
    public String CreateM(@ModelAttribute Motorhome motorhome){
        motorhomeService.createmotorhome(motorhome);
        return "redirect:/motorhome/overview";
    }

    @GetMapping("/motorhome/delete/{motorhome_id}/{motorhome.brand}")
    public String deleteHome(@PathVariable("motorhome_id") int motorhome_id, @PathVariable("motorhome.brand") String brand){
        motorhomeService.deleteMotorhome(motorhome_id);
        return "redirect:/motorhome/details/{motorhome.brand}";
    }

    @GetMapping("/motorhome/details/{brand}")
    public String brandOverview(@PathVariable("brand") String brand, Model model){
        model.addAttribute("motorhomesbrand",motorhomeService.fetchAllMotorhomesByBrand(brand));
        return "/motorhome/details";
    }



}
