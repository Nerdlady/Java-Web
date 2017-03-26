package com.cardealer.controllers;

import com.cardealer.models.bindingModels.user.LoggedUser;
import com.cardealer.models.bindingModels.user.LoginUser;
import com.cardealer.models.bindingModels.user.RegisterUser;
import com.cardealer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("register")
    public String getRegisterPage(Model model) {
        model.addAttribute("view", "user/user-register");
        model.addAttribute("user", new RegisterUser());
        return "base-layout";
    }

    @PostMapping("register")
    public String registerUser(@ModelAttribute RegisterUser registerUser, RedirectAttributes redirectAttributes) {
        List<String> errors = new ArrayList<>();
        LoginUser loginUser = this.userService.getByUsername(registerUser.getUsername());
        if (!registerUser.getPassword().equals(registerUser.getConfirmPassword())) {
            errors.add("Passwords mismatch");
        }

        if (loginUser != null){
            errors.add("Username already exist");
        }

        if (errors.size() > 0){
            redirectAttributes.addFlashAttribute("errors", errors);
            return "redirect:/user/register";
        }

        this.userService.persist(registerUser);
        return "redirect:/user/login";
    }

    @GetMapping("login")
    public String getLoginPage(Model model) {

        model.addAttribute("view", "user/user-login");
        model.addAttribute("user", new LoginUser());
        return "base-layout";
    }

    @PostMapping("login")
    public String loginUser(@ModelAttribute LoginUser loginUser, RedirectAttributes redirectAttributes, HttpSession httpSession) {
        LoggedUser loggedUser = this.userService.getByUsernameAndPassword(loginUser.getUsername(), loginUser.getPassword());
        if (loggedUser == null){
            List<String> errors = new ArrayList<>();
            errors.add("Wrong username or password");
            redirectAttributes.addFlashAttribute("errors",errors);
            return "redirect:/user/login";
        }
        httpSession.setAttribute("user",loggedUser);
        return "redirect:/";
    }

    @GetMapping("logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/";
    }
}
