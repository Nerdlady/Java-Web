package com.residentEvil.controllers;

import com.residentEvil.entities.enums.Magnitude;
import com.residentEvil.entities.enums.Mutation;
import com.residentEvil.models.bindlingModels.CapitalModel;
import com.residentEvil.models.bindlingModels.EditVirusModel;
import com.residentEvil.models.bindlingModels.VirusModel;
import com.residentEvil.models.viewModels.ModifiableVirusView;
import com.residentEvil.models.viewModels.VirusView;
import com.residentEvil.services.CapitalService;
import com.residentEvil.services.VirusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class VirusController {
    private final CapitalService capitalService;

    private final VirusService virusService;

    @Autowired
    public VirusController(CapitalService capitalService, VirusService virusService) {
        this.capitalService = capitalService;
        this.virusService = virusService;
    }


    @ModelAttribute(name = "mutations")
    public List<String> getMutations(){
        List<String> mutationList = new ArrayList<>();
        Mutation[] mutations = Mutation.values();
        for (Mutation mutation : mutations) {
            mutationList.add(mutation.toString());
        }

        return mutationList;
    }

    @ModelAttribute(name = "magnitudes")
    public List<String> getMagnitude(){
        List<String> magnitudeList = new ArrayList<>();
        Magnitude[] magnitudes = Magnitude.values();
        for (Magnitude magnitude : magnitudes) {
            magnitudeList.add(magnitude.toString());
        }

        return magnitudeList;
    }

    @ModelAttribute(name = "capitalList")
    public List<String> getCapitals(){
        return this.capitalService.getCapitalsNames();
    }

    @GetMapping("/viruses/add")
    public String getAddVirus(@ModelAttribute VirusModel virusModel, Model model){
        model.addAttribute("view","add-viruses");
        return "base-layout";
    }

    @PostMapping("/viruses/add")
    public String addVirus(@Valid @ModelAttribute VirusModel virusBindingModel, BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("view","add-viruses");
            return "base-layout";
        }
        Set<CapitalModel> capitalModels = this.capitalService.getAllByName(virusBindingModel.getCapitalsNames());
        virusBindingModel.setCapitals(capitalModels);
        this.virusService.persist(virusBindingModel);

        return "redirect:/";
    }

    @GetMapping("/viruses")
    public String getAllVirusesPage(Model model){
        List<VirusView> virusViews = this.virusService.getAll();
        model.addAttribute("viruses",virusViews);
        model.addAttribute("view","all-viruses");
        return "base-layout";
    }

    @GetMapping("/viruses/edit/{id}")
    public String getEditPage(@PathVariable Long id,Model model){
        ModifiableVirusView modifiableVirusView = this.virusService.getById(id);
        model.addAttribute("view","edit-virus");
        model.addAttribute("editVirusModel",modifiableVirusView);
        return "base-layout";
    }

    @PostMapping("/viruses/edit/{id}")
    public String editVirus(@PathVariable Long id, @Valid @ModelAttribute EditVirusModel virus,BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("view","edit-virus");
            return "base-layout";
        }
        Set<CapitalModel> capitalModels = this.capitalService.getAllByName(virus.getCapitalsNames());
        virus.setCapitals(capitalModels);
        virus.setId(id);
        this.virusService.update(virus);
        return "redirect:/viruses";
    }

    @GetMapping("/viruses/delete/{id}")
    public String getDeletePage(@PathVariable Long id,Model model){
        ModifiableVirusView modifiableVirusView = this.virusService.getById(id);
        model.addAttribute("view","delete-virus");
        model.addAttribute("editVirusModel",modifiableVirusView);
        return "base-layout";
    }

    @PostMapping("/viruses/delete/{id}")
    public String deleteVirus(@PathVariable Long id,Model model){
       this.virusService.delete(id);
        return "redirect:/viruses";
    }
}
