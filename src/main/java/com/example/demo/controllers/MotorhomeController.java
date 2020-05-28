package com.example.demo.controllers;

import com.example.demo.models.Accessory;
import com.example.demo.models.Motorhome;

import com.example.demo.services.AccessoryService;
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

    @Autowired
    AccessoryService accessoryService;

    @GetMapping("/autocamper/oversigt")
    public String overview(Model model){
        model.addAttribute("motorhomes", motorhomeService.fetchAllDistinctMotorhomes());
        return "/motorhome/overview";
    }

    @GetMapping("/autocamper/rediger/{motorhome_id}")
    public String findMotorhome(@PathVariable("motorhome_id")int motorhome_id, Model model){
        model.addAttribute("motorhome",motorhomeService.fetchMotorhomeByID(motorhome_id));
        return "/motorhome/edit";
    }

    @PostMapping("/autocamper/rediger")
    public String update(@ModelAttribute Motorhome motorhome){
        motorhomeService.updateMotorhome(motorhome);
        return "redirect:/autocamper/detaljer/"+motorhome.getBrand();
    }

    @GetMapping("/autocamper/opret")
    public String showCreateMotorhomePage(){
        return "/motorhome/create";
    }

    @PostMapping("/autocamper/opret")
    public String createM(@ModelAttribute Motorhome motorhome){
        motorhomeService.createMotorhome(motorhome);
        return "redirect:/autocamper/oversigt";
    }

    @GetMapping("/autocamper/slet/{motorhome_id}/{motorhome.brand}")
    public String deleteHome(@PathVariable("motorhome_id") int motorhome_id, @PathVariable("motorhome.brand") String brand){
        motorhomeService.deleteMotorhome(motorhome_id);
        return "redirect:/autocamper/detaljer/{motorhome.brand}";
    }

    @GetMapping("/autocamper/detaljer/{brand}")
    public String brandOverview(@PathVariable("brand") String brand, Model model){
        model.addAttribute("motorhomesbrand",motorhomeService.fetchAllMotorhomesByBrand(brand));
        return "/motorhome/details";
    }

    @GetMapping("/tilbehør/oversigt")
    public String overviewA(Model model) {
        model.addAttribute("accessories", accessoryService.fetchAllAccessories());
        return "/accessory/overview";
    }

    @PostMapping("/tilbehør/opret")
    public String createA(@ModelAttribute Accessory accessory) {
        accessoryService.createAccessory(accessory);
        return "redirect:/tilbehør/oversigt";
    }

    @GetMapping("/tilbehør/rediger/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("accessory", accessoryService.fetchAccessoryById(id));
        return "/accessory/edit";
    }

    @PostMapping("/tilbehør/rediger")
    public String editAccessory(@ModelAttribute Accessory accessory) {
        accessoryService.updateAccessory(accessory);
        return "redirect:/tilbehør/oversigt";
    }
}
