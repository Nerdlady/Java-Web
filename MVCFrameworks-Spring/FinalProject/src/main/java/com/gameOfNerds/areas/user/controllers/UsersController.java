package com.gameOfNerds.areas.user.controllers;

import com.gameOfNerds.areas.user.models.viewModels.UserGameInfoViewModel;
import com.gameOfNerds.areas.user.models.viewModels.UserViewModel;
import com.gameOfNerds.areas.user.services.UserGameInfoService;
import com.gameOfNerds.areas.user.services.UserService;
import com.gameOfNerds.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("users")
public class UsersController {
    private final UserGameInfoService userGameInfoService;
    private final UserService userService;

    @Autowired
    public UsersController(UserGameInfoService userGameInfoService, UserService userService) {
        this.userGameInfoService = userGameInfoService;
        this.userService = userService;
    }

    @GetMapping("")
    private String getAllUsersPage(Model model){
        List<UserGameInfoViewModel> userGameInfoViewModels = this.userGameInfoService.findAll();
        model.addAttribute("users",userGameInfoViewModels);
        model.addAttribute(Constants.VIEW,Constants.ALL_USERS_VIEW);
        return Constants.LAYOUT;
    }

    @GetMapping("challenge/{id}")
    public String getChallengePage(@PathVariable Long id, Model model, Principal principal){
        UserViewModel userViewModel = this.userService.getById(id);

        if (userViewModel == null){
            model.addAttribute(Constants.VIEW, Constants.WRONG_PAGE_VIEW);
            return Constants.LAYOUT;
        } else if (userViewModel.getUsername().equals(principal.getName())){
            model.addAttribute(Constants.VIEW, Constants.CAN_NOT_CHALLENGE_YOURSELF_VIEW);
            return Constants.LAYOUT;
        }
        model.addAttribute("challengedUser",userViewModel);
        model.addAttribute(Constants.VIEW,Constants.CHALLENGE_VIEW);
        return Constants.LAYOUT;
    }

    @GetMapping("/profile/{id}")
    public String getProfilePage(@PathVariable Long id, Model model){
        UserGameInfoViewModel userGameInfoViewModel = this.userGameInfoService.getViewByUserId(id);
        if (userGameInfoViewModel == null) {
            model.addAttribute(Constants.VIEW, Constants.WRONG_PAGE_VIEW);
            return Constants.LAYOUT;
        }

        model.addAttribute("user",userGameInfoViewModel);
        model.addAttribute(Constants.VIEW,Constants.PROFILE_VIEW);
        return Constants.LAYOUT;
    }

    @GetMapping("/profile")
    public String getUserProfilePage(Model model,Principal principal){
        UserGameInfoViewModel userGameInfoViewModel = this.userGameInfoService.getViewByUserUsername(principal.getName());
        if (userGameInfoViewModel == null) {
            model.addAttribute(Constants.VIEW, Constants.WRONG_PAGE_VIEW);
            return Constants.LAYOUT;
        }

        model.addAttribute("user",userGameInfoViewModel);
        model.addAttribute(Constants.VIEW,Constants.PROFILE_VIEW);
        return Constants.LAYOUT;
    }
}
