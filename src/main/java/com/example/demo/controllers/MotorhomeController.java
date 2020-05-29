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
    public String showMotorhome(Model model){
        model.addAttribute("motorhomes", motorhomeService.fetchAllDistinctMotorhomes());
        return "/motorhome/overview";
    }

    @GetMapping("/autocamper/opret")
    public String createMotorhome(){
        return "/motorhome/create";
    }

    @PostMapping("/autocamper/opret")
    public String createMotorhomeNow(@ModelAttribute Motorhome motorhome){
        motorhomeService.createMotorhome(motorhome);
        return "redirect:/autocamper/oversigt";
    }

    @GetMapping("/autocamper/detaljer/{brand}/{model}")
    public String motorhomedetails(@PathVariable("brand") String brand, @PathVariable("model") String model, Model viewModel){
        viewModel.addAttribute("motorhomesbrand",motorhomeService.fetchAllMotorhomesByBrandAndModel(brand, model));
        return "/motorhome/details";
    }

    @GetMapping("/autocamper/rediger/{motorhome_id}")
    public String editMotorhome(@PathVariable("motorhome_id")int motorhome_id, Model model){
        model.addAttribute("motorhome",motorhomeService.fetchMotorhomeByID(motorhome_id));
        return "/motorhome/edit";
    }

    @PostMapping("/autocamper/rediger")
    public String editMotorhomeNow(@ModelAttribute Motorhome motorhome){
        motorhomeService.updateMotorhome(motorhome);
        return "redirect:/autocamper/detaljer/"+motorhome.getBrand()+"/"+motorhome.getModel();
    }

    @GetMapping("/autocamper/slet/{motorhome_id}/{motorhome.brand}/{motorhome.model}")
    public String deleteMotorhome(@PathVariable("motorhome_id") int motorhome_id, @PathVariable("motorhome.brand") String brand){
        motorhomeService.deleteMotorhome(motorhome_id);
        return "redirect:/autocamper/detaljer/{motorhome.brand}/{motorhome.model}";
    }

    @GetMapping("/autocamper/tilbehoer/oversigt")
    public String showAccessories(Model model) {
        model.addAttribute("accessories", accessoryService.fetchAllAccessories());
        return "/accessory/overview";
    }

    @PostMapping("/autocamper/tilbehoer/opret")
    public String createAccessory(@ModelAttribute Accessory accessory) {
        accessoryService.createAccessory(accessory);
        return "redirect:/autocamper/tilbehoer/oversigt";
    }

    @GetMapping("/autocamper/tilbehoer/rediger/{id}")
    public String editAccessory(@PathVariable("id") int id, Model model) {
        model.addAttribute("accessory", accessoryService.fetchAccessoryById(id));
        return "/accessory/edit";
    }

    @PostMapping("/autocamper/tilbehoer/rediger")
    public String editAccessoryNow(@ModelAttribute Accessory accessory) {
        accessoryService.updateAccessory(accessory);
        return "redirect:/autocamper/tilbehoer/oversigt";
    }

    @GetMapping("/autocamper/tilbehoer/slet/{id}")
    public String deleteAccessory(@PathVariable("id") int id){
        accessoryService.deleteAccessory(id);
        return "redirect:/autocamper/tilbehoer/oversigt";
    }
}
