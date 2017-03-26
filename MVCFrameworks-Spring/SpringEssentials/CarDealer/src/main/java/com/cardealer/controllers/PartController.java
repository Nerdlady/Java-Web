package com.cardealer.controllers;

import com.cardealer.models.bindingModels.supplier.SupplierModel;
import com.cardealer.models.bindingModels.part.AddPartModel;
import com.cardealer.models.bindingModels.part.EditPartModel;
import com.cardealer.models.viewModels.part.PartView;
import com.cardealer.models.viewModels.supplier.SupplierView;
import com.cardealer.services.PartService;
import com.cardealer.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/parts")
public class PartController {
    @Autowired
    private PartService partService;

    @Autowired
    private SupplierService supplierService;



    @GetMapping("all")
    public String getAllPartsPage(Model model){
        List<PartView> parts = this.partService.getAll();
        model.addAttribute("parts",parts);
        model.addAttribute("view","/parts/parts-table");
        return "base-layout";
    }

    @GetMapping("add")
    public String getAddPartPage(Model model){
        List<SupplierView> supplierViews = this.supplierService.getAll();
        model.addAttribute("suppliers",supplierViews);
        model.addAttribute("type","Add");
        model.addAttribute("part", new AddPartModel());
        model.addAttribute("view","/parts/parts-add");
        return "base-layout";
    }

    @PostMapping("add")
    public String addPart(@ModelAttribute AddPartModel partModel, @RequestParam String supplierName){
        SupplierModel supplierModel = this.supplierService.getByName(supplierName);
        partModel.setSupplier(supplierModel);
        partModel.setQuantity(partModel.getQuantity() == null? 1 : partModel.getQuantity());

        this.partService.persist(partModel);
        return "redirect:/parts/all";
    }

    @GetMapping("edit/{id}")
    public String getEditPage(Model model,@PathVariable Long id){
        EditPartModel editPartModel = this.partService.getById(id);
        model.addAttribute("view","/parts/parts-edit");
        model.addAttribute("part",editPartModel);
        return "base-layout";
    }

    @PostMapping("edit/{id}")
    public String editPart(@ModelAttribute EditPartModel editPartModel,@PathVariable Long id){
        editPartModel.setId(id);
        this.partService.update(editPartModel);
        return "redirect:/parts/all";
    }

    @GetMapping("delete/{id}")
    public String getDeletePartPage(Model model,@PathVariable Long id){
        PartView partView = this.partService.getViewById(id);
        model.addAttribute("view","/parts/parts-delete");
        model.addAttribute("part",partView);
        return "base-layout";
    }

    @PostMapping("delete/{id}")
    public String deletePart(@ModelAttribute EditPartModel editPartModel,@PathVariable Long id){
        editPartModel.setId(id);
        this.partService.delete(editPartModel);
        return "redirect:/parts/all";
    }
}

