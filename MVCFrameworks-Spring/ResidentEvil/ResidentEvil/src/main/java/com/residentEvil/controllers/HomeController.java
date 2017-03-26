package com.residentEvil.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("view", "home");
        return "base-layout";
    }

    @GetMapping("/unauthorized")
    public String getNoAccessPage(Model model) {
        model.addAttribute("view", "no-access");
        return "base-layout";
    }
}
