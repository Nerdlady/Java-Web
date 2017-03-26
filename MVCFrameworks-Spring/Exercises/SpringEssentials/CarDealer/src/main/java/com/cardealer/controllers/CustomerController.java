package com.cardealer.controllers;

import com.cardealer.models.bindingModels.customer.AddCustomerModel;
import com.cardealer.models.bindingModels.customer.EditCustomerModel;
import com.cardealer.models.viewModels.customer.CustomerDetailsView;
import com.cardealer.models.viewModels.customer.CustomerView;
import com.cardealer.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("add")
    public String getAddCustomerPage(Model model){
        AddCustomerModel addCustomerModel = new AddCustomerModel();
        model.addAttribute("customer", addCustomerModel);
        model.addAttribute("view","/customers/customer-modifiable");
        model.addAttribute("type","Add");
        return "base-layout";
    }

    @PostMapping("add")
    public String addCustomer(@ModelAttribute AddCustomerModel addCustomerModel){
       addCustomerModel.setIsYoungDriver(this.isYoungDriver(addCustomerModel.getBirthDate()));
       this.customerService.persist(addCustomerModel);
        return "redirect:/customers/all";
    }

    @GetMapping("all")
    public String getHomePage(Model model, @RequestParam(value = "filter",required = false) String filter){
        List<CustomerView> customerViews = this.customerService.getAllOrderByBirthDate(filter);
        model.addAttribute("customers",customerViews);
        model.addAttribute("view","/customers/customers-table");
        return "base-layout";
    }

    @GetMapping("{id}")
    public String getCustomerDetailsPage(Model model, @PathVariable Long id){
        CustomerDetailsView customerDetailsView = this.customerService.getById(id);
        model.addAttribute("customer",customerDetailsView);
        model.addAttribute("view","/customers/customer-details");
        return "base-layout";
    }

    @GetMapping("edit/{id}")
    public String getEditCustomerPage(Model model,@PathVariable Long id){
        EditCustomerModel customerModel = this.customerService.getByIdToEdit(id);
        model.addAttribute("customer",customerModel);
        model.addAttribute("view","/customers/customer-modifiable");
        model.addAttribute("type","Edit");

        return "base-layout";
    }

    @PostMapping("edit/{id}")
    public String editCustomer(@ModelAttribute EditCustomerModel customerModel,@PathVariable Long id){
        customerModel.setIsYoungDriver(this.isYoungDriver(customerModel.getBirthDate()));
        customerModel.setId(id);
        this.customerService.update(customerModel);
        return "redirect:/customers/all";
    }

    private Boolean isYoungDriver(Date birthday){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthday);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);
        LocalDate pdate = LocalDate.of(year,month,date);
        LocalDate now = LocalDate.now();

        Period diff = Period.between(pdate, now);

        return diff.getYears() < 20;

    }
}
