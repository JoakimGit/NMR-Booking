package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//Lavet af Emil
@Controller
public class HomepageController {

    @GetMapping("/index")
    public String homepage() {
        return "/index";
    }
}