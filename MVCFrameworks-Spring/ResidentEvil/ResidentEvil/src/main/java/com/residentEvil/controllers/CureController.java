package com.residentEvil.controllers;

import com.residentEvil.models.viewModels.ModifiableVirusView;
import com.residentEvil.models.viewModels.VirusView;
import com.residentEvil.services.VirusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CureController {

    @Autowired
    private VirusService virusService;

    @GetMapping("/cures")
    public String getCuresPage(Model model){
        List<VirusView> virusViews = this.virusService.getAll();
        model.addAttribute("viruses",virusViews);
        model.addAttribute("view","all-viruses-cures");
        return "base-layout";
    }

    @GetMapping("/cures/delete/{id}")
    public String getDeletePage(@PathVariable Long id, Model model){
        ModifiableVirusView modifiableVirusView = this.virusService.getById(id);
        model.addAttribute("view","delete-virus");
        model.addAttribute("editVirusModel",modifiableVirusView);
        return "base-layout";
    }

    @PostMapping("/cures/delete/{id}")
    public String deleteVirus(@PathVariable Long id,Model model){
        this.virusService.delete(id);
        return "redirect:/viruses";
    }
}
