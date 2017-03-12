package com.controllers;

import com.constants.Constants;
import com.models.bindingModels.user.LoggedUser;
import com.models.viewModels.game.GameView;
import com.services.GameService;
import com.services.UserService;
import mvcFramework.annotations.controller.Controller;
import mvcFramework.annotations.parameters.RequestParam;
import mvcFramework.annotations.request.GetMapping;
import mvcFramework.models.Model;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.List;

@Stateless
@Controller
public class HomeController {

    @Inject
    private GameService gameService;

    @Inject
    private UserService userService;

    @GetMapping("/")
    public String getHomePage(Model model, @RequestParam("filter") String filter, HttpSession httpSession){
        List<GameView> homeGameViews = this.gameService.getAllHomeGames();
        if ("Owned".equals(filter)){
            LoggedUser loggedUser = (LoggedUser) httpSession.getAttribute("user");
            if (loggedUser == null){
                return "redirect:/login";
            }
            homeGameViews = this.userService.getUserBoughtGames(loggedUser.getId());
        }
        model.addAttribute("games",homeGameViews);
        model.addAttribute(Constants.VIEW,"home.jsp");
        return Constants.BASE_LAYOUT;
    }
}
