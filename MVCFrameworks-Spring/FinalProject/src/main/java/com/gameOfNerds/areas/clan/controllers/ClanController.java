package com.gameOfNerds.areas.clan.controllers;

import com.gameOfNerds.areas.clan.models.bindingModels.ClanModel;
import com.gameOfNerds.areas.clan.models.bindingModels.CreateClanModel;
import com.gameOfNerds.areas.clan.models.viewModels.ClanViewModel;
import com.gameOfNerds.areas.clan.services.ClanService;
import com.gameOfNerds.areas.user.models.bindingModels.UserGameInfoModel;
import com.gameOfNerds.areas.user.models.bindingModels.UserModel;
import com.gameOfNerds.areas.user.models.viewModels.UserWithClanModel;
import com.gameOfNerds.areas.user.services.UserGameInfoService;
import com.gameOfNerds.areas.user.services.UserService;
import com.gameOfNerds.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/clans")
public class ClanController {
    private final ClanService clanService;
    private final UserService userService;
    private final UserGameInfoService userGameInfoService;

    @Autowired
    public ClanController(ClanService clanService, UserService userService, UserGameInfoService userGameInfoService) {
        this.clanService = clanService;
        this.userService = userService;
        this.userGameInfoService = userGameInfoService;
    }

    @GetMapping("all")
    public String getAllClansPage(Model model) {
        List<ClanViewModel> viewModels = this.clanService.getAllClans();
        model.addAttribute("clans", viewModels);
        model.addAttribute(Constants.VIEW, Constants.CLANS_TABLE_VIEW);
        return Constants.LAYOUT;
    }

    @GetMapping("all/{id}")
    public String getClanPage(Model model, @PathVariable Long id, Principal principal) {
        ClanViewModel clanViewModel = this.clanService.getById(id);

        if (clanViewModel == null) {
            model.addAttribute(Constants.VIEW, Constants.WRONG_PAGE_VIEW);
            return Constants.LAYOUT;
        }
        UserGameInfoModel userGameInfoModel = this.userGameInfoService.getByUserUsername(principal.getName());
        boolean hasClan = false;
        if (userGameInfoModel.getClan() != null) {
            hasClan = true;
        }
        model.addAttribute("hasClan", hasClan);
        model.addAttribute("clan", clanViewModel);
        model.addAttribute(Constants.VIEW, Constants.CLAN_VIEW);
        return Constants.LAYOUT;
    }

    @GetMapping("my")
    public String getCurrentUserClanPage(Model model, Principal principal) {
        UserWithClanModel userWithClanModel = this.userGameInfoService.getWithClanByUserUsername(principal.getName());
        if (userWithClanModel == null){
            model.addAttribute(Constants.VIEW,Constants.WRONG_PAGE_VIEW);
            return Constants.LAYOUT;
        }

        if (userWithClanModel.getClan() == null) {
            model.addAttribute(Constants.VIEW, Constants.NO_CLAN_VIEW);
            return Constants.LAYOUT;
        }

        boolean canLeave = false;
        if (!userWithClanModel.getClan().getOwner().getId().equals(userWithClanModel.getUser().getId())) {
            canLeave = true;
        }
        model.addAttribute("canLeave", canLeave);

        model.addAttribute("clan", userWithClanModel.getClan());
        model.addAttribute(Constants.VIEW, Constants.CLAN_VIEW);
        return Constants.LAYOUT;
    }

    @GetMapping("create")
    public String getCreateClanPage(Model model, @ModelAttribute CreateClanModel createClanModel, Principal principal) {
        UserGameInfoModel userGameInfoModel = this.userGameInfoService.getByUserUsername(principal.getName());
        if (userGameInfoModel == null){
            model.addAttribute(Constants.VIEW,Constants.WRONG_PAGE_VIEW);
            return Constants.LAYOUT;
        }
        if (userGameInfoModel.getClan() != null) {
            return "redirect:/clans/my";
        }

        model.addAttribute("clanModel", createClanModel);
        model.addAttribute(Constants.VIEW, Constants.CREATE_CLAN_VIEW);
        return Constants.LAYOUT;
    }

    @PostMapping("create")
    public String createClan(Model model, @Valid @ModelAttribute CreateClanModel createClanModel, BindingResult bindingResult, Principal principal) {
        UserModel userModel = this.userService.findByUsername(principal.getName());
        if (userModel == null){
            model.addAttribute(Constants.VIEW,Constants.WRONG_PAGE_VIEW);
            return Constants.LAYOUT;
        }

        if (bindingResult.hasErrors()) {

            model.addAttribute(Constants.VIEW, Constants.CREATE_CLAN_VIEW);
            return Constants.LAYOUT;
        }

        createClanModel.setOwner(userModel);
        ClanModel clanModel1 = this.clanService.persis(createClanModel);

        UserGameInfoModel userGameInfoModel = new UserGameInfoModel();
        userGameInfoModel.setClan(clanModel1);
        userGameInfoModel.setUser(userModel);
        this.userGameInfoService.setClan(userGameInfoModel);
        return "redirect:/clans/my";
    }

    @GetMapping("join/{id}")
    public String joinClan(@PathVariable Long id, Principal principal) {
        UserGameInfoModel userGameInfoModel = new UserGameInfoModel();

        ClanModel clanModel = this.clanService.getClanById(id);
        if (clanModel == null) {
            return "redirect:/clans/all";
        }
        UserModel userModel = this.userService.findByUsername(principal.getName());
        if (userModel == null){
            return "redirect:/";
        }
        userGameInfoModel.setClan(clanModel);
        userGameInfoModel.setUser(userModel);
        this.userGameInfoService.setClan(userGameInfoModel);
        return "redirect:/clans/my";
    }

    @GetMapping("leave")
    public String leaveClan(Principal principal) {
        UserGameInfoModel userGameInfoModel = new UserGameInfoModel();

        UserModel userModel = this.userService.findByUsername(principal.getName());
        if (userModel == null){
            return "redirect:/";
        }
        userGameInfoModel.setClan(null);
        userGameInfoModel.setUser(userModel);
        this.userGameInfoService.setClan(userGameInfoModel);
        return "redirect:/clans/my";
    }

    @GetMapping("remove/{id}")
    public String removeUserFromClan(@PathVariable Long id, Principal principal) {
        UserModel clanOwner = this.userService.findByUsername(principal.getName());
        ClanModel clanModel = this.clanService.getByOwnerId(clanOwner.getId());
        if (clanModel != null){
            UserGameInfoModel userGameInfoModel = new UserGameInfoModel();
            UserModel userModel = this.userService.findById(id);
            if (userModel == null){
                return "redirect:/clans/my";
            }
            userGameInfoModel.setClan(null);
            userGameInfoModel.setUser(userModel);
            this.userGameInfoService.setClan(userGameInfoModel);
        }
        return "redirect:/clans/my";
    }
}
