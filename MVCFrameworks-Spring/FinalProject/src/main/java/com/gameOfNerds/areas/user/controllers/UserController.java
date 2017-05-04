package com.gameOfNerds.areas.user.controllers;

import com.dropbox.core.DbxException;
import com.gameOfNerds.areas.user.models.bindingModels.RegisterUserModel;
import com.gameOfNerds.areas.user.models.bindingModels.UpdateUser;
import com.gameOfNerds.areas.user.models.bindingModels.UserGameInfoModel;
import com.gameOfNerds.areas.user.models.bindingModels.UserModel;
import com.gameOfNerds.areas.user.services.UserGameInfoService;
import com.gameOfNerds.areas.user.services.UserService;
import com.gameOfNerds.areas.connections.DropBoxUploader;
import com.gameOfNerds.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserController {
    private final UserService userService;
    private final UserGameInfoService userGameInfoService;
    @Autowired
    public UserController(UserService userService, UserGameInfoService userGameInfoService) {
        this.userService = userService;
        this.userGameInfoService = userGameInfoService;
    }

    @GetMapping("/register")
    public String getRegisterPage(@ModelAttribute RegisterUserModel registerUserModel, Model model) {
        model.addAttribute(Constants.VIEW, Constants.REGISTER_VIEW);
        return Constants.LAYOUT;
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute RegisterUserModel registerUserModel, BindingResult bindingResult, Model model, @RequestParam MultipartFile file) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(Constants.VIEW, Constants.REGISTER_VIEW);
            return Constants.LAYOUT;
        }
        try {
            String imageUrl = DropBoxUploader.uploadImage(registerUserModel.getUsername(),file);
            registerUserModel.setImageURL(imageUrl);
        } catch (DbxException e) {
            e.printStackTrace();
        }
        UserModel userModel =  this.userService.persist(registerUserModel);
        UserGameInfoModel userGameInfoModel = new UserGameInfoModel(userModel,0D);
        this.userGameInfoService.persist(userGameInfoModel);
        return "redirect:/login";

    }


    @GetMapping("/login")
    public String getLoginPage(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Wrong username or password");
        }

        model.addAttribute(Constants.VIEW, Constants.LOGIN_VIEW);
        return Constants.LAYOUT;
    }

    @GetMapping("/settings")
    public String getSettingsPage(@ModelAttribute UpdateUser updateUser, Model model) {
        model.addAttribute(Constants.VIEW, Constants.SETTINGS_VIEW);
        return Constants.LAYOUT;
    }

    @PostMapping("/settings")
    public String updateUser(@Valid @ModelAttribute UpdateUser updateUser, BindingResult bindingResult, Model model, @RequestParam MultipartFile file, Principal principal) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("view", Constants.SETTINGS_VIEW);
            return Constants.LAYOUT;
        }
        try {
            String imageUrl = DropBoxUploader.uploadImage(principal.getName(),file);
            updateUser.setImageURL(imageUrl);
        } catch (DbxException e) {
            e.printStackTrace();
        }
        UserModel userModel = this.userService.findByUsername(principal.getName());
        updateUser.setId(userModel.getId());
        this.userService.update(updateUser);
        return "redirect:/";

    }

}
