package com.example.demo.controllers;

import com.example.demo.exceptions.DuplicateExceptionAccessoryName;
import com.example.demo.exceptions.DuplicateExceptionLicensePlate;
import com.example.demo.models.Accessory;
import com.example.demo.models.Motorhome;
import com.example.demo.services.AccessoryService;
import com.example.demo.services.MotorhomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//Lavet af Daniel og Joakim
@Controller
public class MotorhomeController {

    @Autowired
    MotorhomeService motorhomeService;

    @Autowired
    AccessoryService accessoryService;

    @GetMapping("/autocamper/oversigt")
    public String showMotorhome(Model model) {
        model.addAttribute("motorhometypes", motorhomeService.fetchAllMotorhomeTypes());
        return "/motorhome/overview";
    }

    @GetMapping("/autocamper/detaljer/{id}")
    public String motorhomedetails(@PathVariable("id") int id, Model viewModel) {
        viewModel.addAttribute("motorhometype_id", id);
        viewModel.addAttribute("motorhomes",motorhomeService.fetchAllMotorhomesByTypeId(id));
        return "/motorhome/details";
    }

    @GetMapping("/autocamper/opret/type")
    public String createMotorhomeType(){
        return "/motorhome/create-type";
    }

    @PostMapping("/autocamper/opret/type")
    public String createMotorhomeTypeNow(@ModelAttribute Motorhome motorhome) {
        motorhomeService.createMotorhomeType(motorhome);
        return "redirect:/autocamper/oversigt";
    }

    @GetMapping("/autocamper/opret/{id}")
    public String createMotorhome(@PathVariable("id") int id, Model model) {
        model.addAttribute("motorhometype_id", id);
        return "/motorhome/create";
    }

    @PostMapping("/autocamper/opret")
    public String createMotorhomeNow(@ModelAttribute Motorhome motorhome) throws DuplicateExceptionLicensePlate {
        boolean licensePLateExist = motorhomeService.checkForDuplicateLicensePlate(motorhome.getLicense_plate());
        if(licensePLateExist){
            throw new DuplicateExceptionLicensePlate("/autocamper/opret/"+motorhome.getMotorhometype_id());
        }
        motorhomeService.createMotorhome(motorhome);
        return "redirect:/autocamper/detaljer/"+motorhome.getMotorhometype_id();
    }

    @GetMapping("/autocamper/rediger/type/{motorhome_id}")
    public String editMotorhomeType(@PathVariable("motorhome_id") int id, Model model) {
        model.addAttribute("motorhometype", motorhomeService.fetchMotorhomeTypeById(id));
        return "/motorhome/edit-type";
    }

    @PostMapping("/autocamper/rediger/type")
    public String editMotorhomeTypeNow(@ModelAttribute Motorhome motorhome) {
        System.out.println(motorhome);
        motorhomeService.updateMotorhomeType(motorhome);
        return "redirect:/autocamper/oversigt";
    }

    @GetMapping("/autocamper/rediger/{motorhome_id}")
    public String editMotorhome(@PathVariable("motorhome_id") int motorhome_id, Model model) {
        model.addAttribute("motorhome", motorhomeService.fetchMotorhomeByID(motorhome_id));
        return "/motorhome/edit";
    }

    @PostMapping("/autocamper/rediger")
    public String editMotorhomeNow(@ModelAttribute Motorhome motorhome)throws DuplicateExceptionLicensePlate {
        boolean licensePLateExist = motorhomeService.checkForOtherDuplicateLicensePlate(motorhome.getLicense_plate(), motorhome.getMotorhome_id());
        if(licensePLateExist){
            throw new DuplicateExceptionLicensePlate("/autocamper/rediger/detaljer/"+motorhome.getMotorhometype_id());
        }
        motorhomeService.updateMotorhome(motorhome);
        return "redirect:/autocamper/detaljer/"+motorhome.getMotorhometype_id();
    }

    @GetMapping("/autocamper/slet//type/{motorhometype_id}")
    public String deleteMotorhomeType(@PathVariable("motorhometype_id") int id) {
        motorhomeService.deleteMotorhomeType(id);
        return "redirect:/autocamper/oversigt";
    }

    @GetMapping("/autocamper/slet/{motorhometype_id}/{motorhome_id}")
    public String deleteMotorhome(@PathVariable("motorhometype_id") int motorhometype_id, @PathVariable("motorhome_id") int id) {
        motorhomeService.deleteMotorhome(id);
        return "redirect:/autocamper/detaljer/"+motorhometype_id;
    }

    @GetMapping("/autocamper/tilbehoer/oversigt")
    public String showAccessories(Model model) {
        model.addAttribute("accessories", accessoryService.fetchAllAccessories());
        return "/accessory/overview";
    }

    @PostMapping("/autocamper/tilbehoer/opret")
    public String createAccessory(@ModelAttribute Accessory accessory) throws DuplicateExceptionAccessoryName {
        boolean accessoryNameExist = accessoryService.checkForDuplicateAccessoryName(accessory.getAccessory_name());
        if(accessoryNameExist){
            throw new DuplicateExceptionAccessoryName("/autocamper/tilbehoer/oversigt");
        }
        accessoryService.createAccessory(accessory);
        return "redirect:/autocamper/tilbehoer/oversigt";
    }

    @GetMapping("/autocamper/tilbehoer/rediger/{id}")
    public String editAccessory(@PathVariable("id") int id, Model model) {
        model.addAttribute("accessory", accessoryService.fetchAccessoryById(id));
        return "/accessory/edit";
    }

    @PostMapping("/autocamper/tilbehoer/rediger")
    public String editAccessoryNow(@ModelAttribute Accessory accessory) throws DuplicateExceptionAccessoryName {
        boolean accessoryNameExist = accessoryService.checkForOtherDuplicateAccessoryName(accessory.getAccessory_name(), accessory.getAccessory_id());
        if(accessoryNameExist){
            throw new DuplicateExceptionAccessoryName("/autocamper/tilbehoer/rediger/"+accessory.getAccessory_id());
        }
        accessoryService.updateAccessory(accessory);
        return "redirect:/autocamper/tilbehoer/oversigt";
    }

    @GetMapping("/autocamper/tilbehoer/slet/{id}")
    public String deleteAccessory(@PathVariable("id") int id) {
        accessoryService.deleteAccessory(id);
        return "redirect:/autocamper/tilbehoer/oversigt";
    }

    @ExceptionHandler(DuplicateExceptionLicensePlate.class)
    public String databaseError(Model model, DuplicateExceptionLicensePlate exception) {
        model.addAttribute("tilbage", exception.getMessage());
        return "/error/duplicate-exception-Licenseplate";
    }

    @ExceptionHandler(DuplicateExceptionAccessoryName.class)
    public String databaseError(Model model, DuplicateExceptionAccessoryName exception) {
        model.addAttribute("tilbage", exception.getMessage());
        return "/error/duplicate-exception-accessoryname";
    }
}