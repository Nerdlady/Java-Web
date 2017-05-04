package com.gameOfNerds.controllers;

import com.gameOfNerds.areas.user.entities.User;
import com.gameOfNerds.areas.user.models.viewModels.UserViewModel;
import com.gameOfNerds.areas.user.services.UserService;
import com.gameOfNerds.areas.user.services.UsersFightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@ControllerAdvice
public class AttributesController {

    private final UsersFightService usersFightService;
    private final UserService userService;

    @Autowired
    public AttributesController(UsersFightService usersFightService, UserService userService) {
        this.usersFightService = usersFightService;
        this.userService = userService;
    }

    @ModelAttribute(name = "img")
    public String getImage(Principal principal){
        String img = "/img/find_user.png";
        if (principal != null){
            UserViewModel userModel = this.userService.getByUsername(principal.getName());
            if (userModel != null) {
                if (userModel.getImageURL() != null){
                    img = userModel.getImageURL();
                }
            }
        }

        return img;
    }


    @ModelAttribute(name = "challengerChallenges")
    public Integer getChallengerChallengesCount(){
        int count = 0;
        if (SecurityContextHolder.getContext().getAuthentication() != null){
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (principal instanceof UserDetails) {
                User user = ((User)principal);
                count = this.usersFightService.getChallengerChallengesCount(user.getId());
            }
        }
        return count;
    }

    @ModelAttribute(name = "challengedChallenges")
    public Integer getChallengedChallengesCount(){
        int count = 0;
        if (SecurityContextHolder.getContext().getAuthentication() != null){
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (principal instanceof UserDetails) {
                User user = ((User)principal);
                count = this.usersFightService.getChallengedChallengesCount(user.getId());
            }
        }
        return count;
    }
}
