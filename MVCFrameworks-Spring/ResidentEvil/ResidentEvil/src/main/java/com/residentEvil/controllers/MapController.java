package com.residentEvil.controllers;

import com.residentEvil.services.VirusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapController {

    @Autowired
    private VirusService virusService;

    @GetMapping("/map")
    public String getMapPage(Model model){
        String geoJson = this.virusService.getGeoData();
        model.addAttribute("geoJson", geoJson);
        return "map";
    }
}
