package com.cardealer.controllers;

import com.cardealer.models.viewModels.log.LogView;
import com.cardealer.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/logs")
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("all")
    public String getAllLogsPage(Model model, @RequestParam(value = "username",required = false) String username){
        List<LogView> logViews = this.logService.getAllByUsername(username);
        model.addAttribute("logs",logViews);
        model.addAttribute("view","/logs/logs-table");
        return "base-layout";
    }

    @GetMapping("clear")
    public String clearLogs(){
        this.logService.deleteAllLogs();
        return "redirect:/logs/all";
    }
}
