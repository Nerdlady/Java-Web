package com.gameOfNerds.controllers;

import com.gameOfNerds.constants.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping("/")
    public String getHomePage(Model model){

        model.addAttribute(Constants.VIEW,Constants.HOME_VIEW);
        return Constants.LAYOUT;
    }

    @GetMapping("/unauthorized")
    public String getNoAccessPage(Model model) {
        model.addAttribute(Constants.VIEW, Constants.WRONG_PAGE_VIEW);
        return Constants.LAYOUT;
    }
}
