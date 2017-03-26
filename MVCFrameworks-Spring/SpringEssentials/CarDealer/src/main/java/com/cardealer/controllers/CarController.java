package com.cardealer.controllers;

import com.cardealer.entities.enums.Operation;
import com.cardealer.models.bindingModels.car.CarModel;
import com.cardealer.models.bindingModels.log.LogModel;
import com.cardealer.models.bindingModels.part.PartModel;
import com.cardealer.models.bindingModels.user.LoggedUser;
import com.cardealer.models.viewModels.car.CarInfoView;
import com.cardealer.models.viewModels.car.CarWithPartsView;
import com.cardealer.models.viewModels.part.PartView;
import com.cardealer.services.CarService;
import com.cardealer.services.LogService;
import com.cardealer.services.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("cars")
public class CarController {
    @Autowired
    private CarService carService;

    @Autowired
    private PartService partService;

    @Autowired
    private LogService logService;

    @GetMapping("all")
    public String getCarsTablePage(Model model, @RequestParam(value = "make",required = false) String make){
        List<CarInfoView> carInfoViews = this.carService.getAllByMake(make);
        model.addAttribute("cars", carInfoViews);
        model.addAttribute("view","/cars/cars-table");
        return "base-layout";
    }

    @GetMapping("{id}/parts")
    public String getCarPartsPage(Model model, @PathVariable Long id){
        CarWithPartsView car = this.carService.getById(id);
        model.addAttribute("car",car);
        model.addAttribute("view","/cars/cars-parts-table");
        return "base-layout";
    }

    @GetMapping("add")
    public String getAddCarPage(Model model){
        List<PartView> partViews = this.partService.getAll();
        model.addAttribute("parts",partViews);
        model.addAttribute("car",new CarModel());
        model.addAttribute("view","/cars/cars-add");

        return "base-layout";
    }

    @PostMapping("add")
    public String addCarPage(@ModelAttribute CarModel carModel, @RequestParam String[] partsNames, HttpSession httpSession){
        Set<PartModel> partModels= new HashSet<>();
        for (String part : partsNames) {
            PartModel partModel = this.partService.getByName(part);
            partModels.add(partModel);
        }

        carModel.setParts(partModels);
        this.carService.persist(carModel);
        LoggedUser loggedUser = (LoggedUser) httpSession.getAttribute("user");
        LogModel logModel = new LogModel(loggedUser,"Cars", Operation.ADD,new Date());
        this.logService.persist(logModel);
        return "redirect:/cars/all";
    }


}
