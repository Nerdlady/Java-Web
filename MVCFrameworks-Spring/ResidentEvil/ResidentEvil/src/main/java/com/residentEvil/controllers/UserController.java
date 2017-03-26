package com.residentEvil.controllers;

import com.residentEvil.models.bindlingModels.EditUserModel;
import com.residentEvil.models.bindlingModels.RegisterUserModel;
import com.residentEvil.models.bindlingModels.RoleModel;
import com.residentEvil.models.viewModels.UserModel;
import com.residentEvil.services.RoleService;
import com.residentEvil.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/register")
    public String getRegisterPage(@ModelAttribute RegisterUserModel registerUserModel,Model model){
        model.addAttribute("view","register-user");
        return "base-layout";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute RegisterUserModel registerUserModel, BindingResult bindingResult,Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("view","register-user");
            return "base-layout";
        }

        this.userService.persist(registerUserModel);

        return "redirect:/login";

    }

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(required = false)String error,Model model){
        if (error != null){
            model.addAttribute("error","Wrong username or password");
        }

        model.addAttribute("view","login-user");
        return "base-layout";
    }

    @GetMapping("/users")
    public String getAllUsesPage(Model model){
        List<UserModel> users = this.userService.getAll();
        model.addAttribute("users",users);
        model.addAttribute("view","all-users");
        return "base-layout";
    }

    @ModelAttribute(name = "roles")
    private List<RoleModel> getRoles(){
        return this.roleService.getAll();
    }

    @GetMapping("/users/edit/{id}")
    private String getEditUserPage(Model model, @PathVariable Long id){
        EditUserModel editUserModel = this.userService.getById(id);
        model.addAttribute("user",editUserModel);
        model.addAttribute("view","edit-user");
        return "base-layout";
    }

    @PostMapping("/users/edit/{id}")
    public String  editUser(@PathVariable Long id,@ModelAttribute EditUserModel editUserModel){
        editUserModel.setId(id);
        this.userService.edit(editUserModel);
        return "redirect:/users";
    }

}
