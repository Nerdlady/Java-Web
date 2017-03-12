package com.controllers;

import com.constants.Constants;
import com.models.bindingModels.game.EditGame;
import com.models.bindingModels.user.LoggedUser;
import com.models.bindingModels.user.LoginUser;
import com.models.bindingModels.user.RegisterUser;
import com.models.viewModels.game.GameView;
import com.services.GameService;
import com.services.UserService;
import com.utils.DataValidator;
import mvcFramework.annotations.controller.Controller;
import mvcFramework.annotations.parameters.ModelAttribute;
import mvcFramework.annotations.parameters.PathVariable;
import mvcFramework.annotations.request.GetMapping;
import mvcFramework.annotations.request.PostMapping;
import mvcFramework.models.Model;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@Stateless
public class UserController {
    @Inject
    private UserService userService;

    @Inject
    private GameService gameService;

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute(Constants.VIEW, "/user/register.jsp");
        return Constants.BASE_LAYOUT;
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute() RegisterUser registerUser, Model model) {
        Set<String> errors = DataValidator.validateData(registerUser);
        this.checkPasswords(registerUser, errors);
        if (errors.size() > 0) {
            model.addAttribute(Constants.VIEW, "/user/register.jsp");
            model.addAttribute("errors", errors);
            return Constants.BASE_LAYOUT;
        }
        this.userService.save(registerUser);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute(Constants.VIEW, "/user/login.jsp");
        return Constants.BASE_LAYOUT;
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute() LoginUser loginUser, Model model, HttpSession httpSession) {
        Set<String> errors = DataValidator.validateData(loginUser);
        LoggedUser loggedUser = this.userService.getByEmailAndPassword(loginUser.getEmail(), loginUser.getPassword());

        this.checkUserExist(loggedUser, errors);
        if (errors.size() > 0) {
            model.addAttribute(Constants.VIEW, "/user/login.jsp");
            model.addAttribute("errors", errors);
            return Constants.BASE_LAYOUT;
        }

        List<Long> gamesIds = (List<Long>) httpSession.getAttribute("gamesToBuy");

        if (gamesIds != null){
            for (Long id : gamesIds) {
                EditGame editGame = this.gameService.getByIdToEdit(id);
                editGame.getUsersCart().add(loggedUser);
                this.gameService.update(editGame);
            }
        }


        httpSession.setAttribute("user", loggedUser);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }

    @GetMapping("/cart")
    public String getProfilePage(Model model, HttpSession httpSession) {
        LoggedUser loggedUser = (LoggedUser) httpSession.getAttribute("user");
        List<GameView> gameViews = new ArrayList<>();
        if (loggedUser == null) {
            List<Long> gamesIds = (List<Long>) httpSession.getAttribute("gamesToBuy");
            if (gamesIds != null) {
                for (Long id : gamesIds) {
                    GameView gameView = this.gameService.getGameViewById(id);
                    gameViews.add(gameView);
                }
            }

        } else {
            gameViews = this.userService.getUserCartGames(loggedUser.getId());
            model.addAttribute("games", gameViews);
        }

        model.addAttribute("games", gameViews);
        model.addAttribute(Constants.VIEW, "/user/cart.jsp");
        return Constants.BASE_LAYOUT;
    }

    @PostMapping("/cart")
    public String orderGames(HttpSession httpSession) {
        LoggedUser loggedUser = (LoggedUser) httpSession.getAttribute("user");

        if (loggedUser == null) {
            return "redirect:/login";
        }

        List<GameView> cartGames = this.userService.getUserCartGames(loggedUser.getId());

        for (GameView cartGame : cartGames) {
            EditGame editGame = this.gameService.getByIdToEdit(cartGame.getId());
            editGame.getUsersBought().add(loggedUser);
            LoggedUser editUser = editGame.getUsersCart().stream().filter(editUser1 -> editUser1.getId().equals(loggedUser.getId())).findFirst().get();

            editGame.getUsersCart().remove(editUser);

            this.gameService.update(editGame);
        }

        return "redirect:/";
    }

    @GetMapping("/cart/delete/{id}")
    public String deleteFromCart(@PathVariable("id") Long id, HttpSession httpSession) {
        LoggedUser loggedUser = (LoggedUser) httpSession.getAttribute("user");
        if (loggedUser == null) {
            List<Long> gamesIds = (List<Long>) httpSession.getAttribute("gamesToBuy");
            if (gamesIds != null) {
                gamesIds.remove(id);
            }
        } else {
            EditGame editGame = this.gameService.getByIdToEdit(id);
            LoggedUser editUser = editGame.getUsersCart().stream().filter(editUser1 -> editUser1.getId().equals(loggedUser.getId())).findFirst().get();

            editGame.getUsersCart().remove(editUser);

            this.gameService.update(editGame);
        }
        return "redirect:/cart";
    }

    private void checkUserExist(LoggedUser user, Set<String> errors) {
        if (user == null) {
            errors.add(Constants.WRONG_USERNAME_OR_PASSWORD);
        }
    }

    private void checkPasswords(RegisterUser user, Set<String> errors) {
        if (user.getPassword() == null || !user.getPassword().equals(user.getConfirmPassword())) {
            errors.add(Constants.PASSWORDS_MISMATCH);
        }
    }
}
