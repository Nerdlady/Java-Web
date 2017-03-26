package com.cardealer.controllers;

import com.cardealer.entities.enums.Operation;
import com.cardealer.models.bindingModels.car.RelatedCarModel;
import com.cardealer.models.bindingModels.customer.RelatedCustomerModel;
import com.cardealer.models.bindingModels.log.LogModel;
import com.cardealer.models.bindingModels.sale.SaleModel;
import com.cardealer.models.bindingModels.user.LoggedUser;
import com.cardealer.models.viewModels.car.CarView;
import com.cardealer.models.viewModels.car.CarWithPartsView;
import com.cardealer.models.viewModels.customer.CustomerDriverView;
import com.cardealer.models.viewModels.customer.CustomerNameView;
import com.cardealer.models.viewModels.sale.FinalizeSaleView;
import com.cardealer.models.viewModels.sale.SaleView;
import com.cardealer.services.CarService;
import com.cardealer.services.CustomerService;
import com.cardealer.services.LogService;
import com.cardealer.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("sales")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CarService carService;

    @Autowired
    private LogService logService;

    @GetMapping("all")
    public String getAllSalesPage(Model model, @RequestParam(required = false) String discount) {
        List<SaleView> saleViews = this.getSales(discount);
        model.addAttribute("sales", saleViews);
        model.addAttribute("view", "/sales/all-sales");
        return "base-layout";
    }


    @GetMapping("{id}")
    public String getSalePage(Model model, @PathVariable Long id) {
        SaleView saleView = this.saleService.getById(id);
        model.addAttribute("sale", saleView);
        model.addAttribute("view", "/sales/sale-details");
        return "base-layout";
    }

    @GetMapping("all/discounted")
    public String getAllDiscountedPage(Model model, @RequestParam(required = false) String discount) {
        List<SaleView> saleViews = this.getSales(discount);
        model.addAttribute("sales", saleViews);
        model.addAttribute("view", "/sales/all-sales");
        return "base-layout";
    }

    @GetMapping("add")
    public String getAddSalePage(Model model) {
        List<CarView> cars = this.carService.getAll();
        List<CustomerNameView> customers = this.customerService.getAll();

        model.addAttribute("cars", cars);
        model.addAttribute("customers", customers);
        model.addAttribute("sale", new SaleModel());
        model.addAttribute("view", "sales/sale-add");

        return "base-layout";
    }

    @PostMapping("add")
    private String addSale(@ModelAttribute SaleModel saleModel,
                           @RequestParam String customerName,
                           @RequestParam String carName,
                           RedirectAttributes redirectAttributes) {
        RelatedCarModel relatedCarModel = this.carService.getByMake(carName);
        RelatedCustomerModel relatedCustomerModel = this.customerService.getByName(customerName);
        saleModel.setCar(relatedCarModel);
        saleModel.setCustomer(relatedCustomerModel);
        redirectAttributes.addFlashAttribute("sale", saleModel);
        return "redirect:/sales/add/finalize";
    }

    @GetMapping("add/finalize")
    public String getFinalizeSalePage(Model model) {
        SaleModel saleModel = (SaleModel) model.asMap().get("sale");
        CarWithPartsView carWithPartsView = this.carService.getById(saleModel.getCar().getId());
        CustomerDriverView customerDriverView = this.customerService.getDriverById(saleModel.getCustomer().getId());

        final double[] carPrice = {0};
        carWithPartsView.getParts().stream().forEach(car -> carPrice[0] += car.getPrice());
        double totalPrice = carPrice[0] * (1 - (saleModel.getDiscount() + (customerDriverView.getIsYoungDriver() ? 0.05 : 0)));

        FinalizeSaleView finalizeSaleView = new FinalizeSaleView();
        finalizeSaleView.setDiscount(saleModel.getDiscount());
        finalizeSaleView.setCar(carWithPartsView);
        finalizeSaleView.setCustomer(customerDriverView);
        finalizeSaleView.setCarPrice(carPrice[0]);
        finalizeSaleView.setFinalCarPrice(totalPrice);

        model.addAttribute("sale", finalizeSaleView);
        model.addAttribute("view", "sales/sale-finalize");
        return "base-layout";
    }

    @PostMapping("add/finalize")
    public String finalizeSale(@ModelAttribute SaleModel saleModel,
                               @RequestParam String customerName,
                               @RequestParam String carName,
                               HttpSession httpSession) {
        RelatedCarModel relatedCarModel = this.carService.getByMake(carName);
        RelatedCustomerModel relatedCustomerModel = this.customerService.getByName(customerName);

        saleModel.setCar(relatedCarModel);
        saleModel.setCustomer(relatedCustomerModel);
        this.saleService.persist(saleModel);

        LoggedUser loggedUser = (LoggedUser) httpSession.getAttribute("user");
        LogModel logModel = new LogModel(loggedUser, "Sales", Operation.ADD, new Date());
        this.logService.persist(logModel);
        return "redirect:/sales/all";
    }

    private List<SaleView> getSales(String discount) {
        List<SaleView> saleViews = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\d\\.\\d{1,2}");
        Matcher matcher = pattern.matcher(discount == null ? "" : discount);
        if (matcher.find()) {
            Float percent = Float.parseFloat(discount);
            saleViews = this.saleService.getAllByDiscount(percent);
        } else {
            saleViews = this.saleService.getAll();
        }

        return saleViews;
    }
}
