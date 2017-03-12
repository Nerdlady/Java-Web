package com.controllers;

import com.constants.Constants;
import com.models.bindingModels.game.EditGame;
import com.models.bindingModels.game.NewGame;
import com.models.bindingModels.user.LoggedUser;
import com.models.viewModels.game.AdminGameView;
import com.models.viewModels.game.GameDetailsView;
import com.models.viewModels.game.ModifiableGameView;
import com.services.GameService;
import com.utils.DataValidator;
import mvcFramework.annotations.controller.Controller;
import mvcFramework.annotations.parameters.ModelAttribute;
import mvcFramework.annotations.parameters.PathVariable;
import mvcFramework.annotations.parameters.RequestParam;
import mvcFramework.annotations.request.GetMapping;
import mvcFramework.annotations.request.PostMapping;
import mvcFramework.models.Model;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Stateless
@Controller
public class GameController {
    @Inject
    private GameService gameService;

    @GetMapping("/admin/games/add")
    public String getAddGamePage(Model model) {
        model.addAttribute(Constants.VIEW, "/admin/add-game.jsp");
        return Constants.BASE_LAYOUT;
    }

    @PostMapping("/admin/games/add")
    public String addGame(Model model, @ModelAttribute() NewGame newGame, @RequestParam("date") String date) {
        Set<String> errors = DataValidator.validateData(newGame);
        DateFormat format = new SimpleDateFormat("YYYY-MM-dd");

        if (errors.size() > 0) {
            model.addAttribute(Constants.VIEW, "/admin/add-game.jsp");
            model.addAttribute("errors", errors);
            return Constants.BASE_LAYOUT;
        }

        Date date1 = null;
        try {
            date1 = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        newGame.setReleaseDate(date1);
        this.gameService.save(newGame);
        return "redirect:/";
    }

    @GetMapping("/admin/games")
    public String getAdminGamesPage(Model model) {
        List<AdminGameView> games = this.gameService.getAllAdminGames();
        model.addAttribute("games", games);
        model.addAttribute(Constants.VIEW, "/admin/admin-games.jsp");
        return Constants.BASE_LAYOUT;
    }

    @GetMapping("/admin/games/edit/{id}")
    public String getEditGamePage(Model model, @PathVariable("id") Long id) {
        ModifiableGameView gameView = this.gameService.getModifiableGameById(id);
        if (gameView == null) {
            return "redirect:/admin/games";
        }

        model.addAttribute("game", gameView);
        model.addAttribute(Constants.VIEW, "/admin/edit-game.jsp");
        return Constants.BASE_LAYOUT;
    }

    @PostMapping("/admin/games/edit/{id}")
    public String editGame(Model model, @PathVariable("id") Long id, @ModelAttribute() EditGame editGame, @RequestParam("date") String date) {
        Set<String> errors = DataValidator.validateData(editGame);
        DateFormat format = new SimpleDateFormat("YYYY-MM-dd");

        if (errors.size() > 0) {
            model.addAttribute(Constants.VIEW, "/admin/edit-game.jsp");
            model.addAttribute("errors", errors);
            return Constants.BASE_LAYOUT;
        }
        Date date1 = null;
        try {
            date1 = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        editGame.setReleaseDate(date1);
        editGame.setId(id);
        this.gameService.update(editGame);

        return "redirect:/admin/games";
    }

    @GetMapping("/admin/games/delete/{id}")
    public String getDeleteGamePage(Model model, @PathVariable("id") Long id) {
        ModifiableGameView gameView = this.gameService.getModifiableGameById(id);
        if (gameView == null) {
            return "redirect:/admin/games";
        }

        model.addAttribute("game", gameView);
        model.addAttribute(Constants.VIEW, "/admin/delete-game.jsp");
        return Constants.BASE_LAYOUT;
    }

    @PostMapping("/admin/games/delete/{id}")
    public String deleteGame(@PathVariable("id") Long id) {
        this.gameService.delete(id);
        return "redirect:/admin/games";
    }

    @GetMapping("/games/details/{id}")
    public String getDetailsPage(Model model, @PathVariable("id") Long id) {
        GameDetailsView gameView = this.gameService.getDetailsGameById(id);
        model.addAttribute("game", gameView);
        model.addAttribute(Constants.VIEW, "/game/game-details.jsp");
        return Constants.BASE_LAYOUT;
    }

    @GetMapping("/games/buy/{id}")
    public String buyGame(@PathVariable("id") Long id, HttpSession httpSession) {
        LoggedUser loggedUser = (LoggedUser) httpSession.getAttribute("user");
        if (loggedUser == null) {
            List<Long> gamesToBuy = (List<Long>) httpSession.getAttribute("gamesToBuy");
            if (gamesToBuy == null) {
                gamesToBuy = new ArrayList<>();
            }

            if (!gamesToBuy.contains(id)){
                gamesToBuy.add(id);
            }

            httpSession.setAttribute("gamesToBuy",gamesToBuy);
        } else {
            EditGame editGame = this.gameService.getByIdToEdit(id);
            LoggedUser checkUserBoughtGame = editGame.getUsersBought().stream().filter(user -> user.getId().equals(loggedUser.getId())).findFirst().orElse(null);
            LoggedUser checkUserOwnedGame = editGame.getUsersCart().stream().filter(user -> user.getId().equals(loggedUser.getId())).findFirst().orElse(null);
            if (checkUserBoughtGame == null && checkUserOwnedGame == null){
                editGame.getUsersCart().add(loggedUser);
            }

            this.gameService.update(editGame);
        }
        return "redirect:/";
    }
}
