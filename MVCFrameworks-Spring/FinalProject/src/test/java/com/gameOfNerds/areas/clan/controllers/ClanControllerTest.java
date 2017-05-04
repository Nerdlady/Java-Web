package com.gameOfNerds.areas.clan.controllers;

import com.gameOfNerds.areas.clan.models.bindingModels.ClanModel;
import com.gameOfNerds.areas.clan.models.viewModels.ClanViewModel;
import com.gameOfNerds.areas.clan.services.ClanService;
import com.gameOfNerds.areas.user.models.bindingModels.UserGameInfoModel;
import com.gameOfNerds.areas.user.models.bindingModels.UserModel;
import com.gameOfNerds.areas.user.models.viewModels.UserViewModel;
import com.gameOfNerds.areas.user.models.viewModels.UserWithClanModel;
import com.gameOfNerds.areas.user.services.UserGameInfoService;
import com.gameOfNerds.areas.user.services.UserService;
import com.gameOfNerds.areas.user.services.UsersFightService;
import com.gameOfNerds.constants.Constants;
import com.gameOfNerds.interceptors.RefreshQuestionsInterceptor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ClanController.class)
@ActiveProfiles("test")
@ContextConfiguration
public class ClanControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @MockBean
    private ClanService clanService;

    @MockBean
    private Principal principal;

    @MockBean
    private RefreshQuestionsInterceptor refreshQuestionsInterceptor;

    @MockBean
    private UserService userService;

    @MockBean
    private UserGameInfoService userGameInfoService;

    @MockBean
    private UsersFightService usersFightService;

    @Before
    public void setUp() throws Exception {
        this.mvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())
                .build();

        ClanViewModel clanViewModel = new ClanViewModel();
        clanViewModel.setId(1L);
        when(this.clanService.getById(1L)).thenReturn(clanViewModel);

        UserGameInfoModel userGameInfoModel = new UserGameInfoModel();
        userGameInfoModel.setScore(15.00);
        when(this.userGameInfoService.getByUserUsername("Test")).thenReturn(userGameInfoModel);
        when(this.principal.getName()).thenReturn("Test");

    }


    @Test
    public void getClanPageWithExistingId_shouldReturnClanView() throws Exception {
        this.mvc.perform(get("/clans/all/1").principal(this.principal))
                .andExpect(status().isOk())
                .andExpect(model().attribute(Constants.VIEW, "/views/clan/clan"))
                .andExpect(model().attribute("clan", hasProperty("id", is(1L))));
    }

    @Test
    public void getClanPageWithNonExistingId_shouldReturnNoAccessView() throws Exception {
        this.mvc.perform(get("/clans/all/2").principal(this.principal))
                .andExpect(status().isOk())
                .andExpect(model().attribute(Constants.VIEW, "/views/wrong-page"));
    }

    @Test
    public void getClanPageWithExistingIdPrincipalWithoutClan_shouldReturnHasClanAttributeFalse() throws Exception {
        this.mvc.perform(get("/clans/all/1").principal(this.principal))
                .andExpect(status().isOk())
                .andExpect(model().attribute("hasClan", false));
    }

    @Test
    public void getClanPageWithExistingIdPrincipalWithClan_shouldReturnHasClanAttributeTrue() throws Exception {
        UserGameInfoModel userGameInfoModel = new UserGameInfoModel();
        userGameInfoModel.setScore(15.00);
        ClanModel clanModel = new ClanModel();
        clanModel.setId(1L);
        userGameInfoModel.setClan(clanModel);
        when(this.userGameInfoService.getByUserUsername("Test")).thenReturn(userGameInfoModel);
        this.mvc.perform(get("/clans/all/1").principal(this.principal))
                .andExpect(status().isOk())
                .andExpect(model().attribute("hasClan", true));
    }

    @Test
    public void getMyClanPageWithPrincipalWithoutClan_shouldReturnNoClanView() throws Exception {
        UserWithClanModel userWithClanModel = new UserWithClanModel();
        when(this.userGameInfoService.getWithClanByUserUsername("Test")).thenReturn(userWithClanModel);
        this.mvc.perform(get("/clans/my").principal(this.principal))
                .andExpect(status().isOk())
                .andExpect(model().attribute(Constants.VIEW, "/views/clan/no-clan"));
    }

    @Test
    public void getMyClanPageWithPrincipalNotInDatabase_shouldReturnNoAccessView() throws Exception {
        this.mvc.perform(get("/clans/my").principal(this.principal))
                .andExpect(status().isOk())
                .andExpect(model().attribute(Constants.VIEW, "/views/wrong-page"));
    }

    @Test
    public void getMyClanPageWithPrincipalWithClan_shouldReturnClanView() throws Exception {
        UserModel userModel = new UserModel();
        userModel.setId(1L);

        ClanViewModel clanViewModel = new ClanViewModel();
        clanViewModel.setId(1L);
        clanViewModel.setOwner(userModel);

        UserViewModel userViewModel = new UserViewModel();
        userViewModel.setId(1L);

        UserWithClanModel userWithClanModel = new UserWithClanModel();
        userWithClanModel.setClan(clanViewModel);
        userWithClanModel.setUser(userViewModel);

        when(this.userGameInfoService.getWithClanByUserUsername("Test")).thenReturn(userWithClanModel);
        this.mvc.perform(get("/clans/my").principal(this.principal))
                .andExpect(status().isOk())
                .andExpect(model().attribute(Constants.VIEW, "/views/clan/clan"));
    }

    @Test
    public void getMyClanPageWithPrincipalClanOwner_shouldReturnCanLeaveFalse() throws Exception {
        UserModel userModel = new UserModel();
        userModel.setId(1L);

        ClanViewModel clanViewModel = new ClanViewModel();
        clanViewModel.setId(1L);
        clanViewModel.setOwner(userModel);

        UserViewModel userViewModel = new UserViewModel();
        userViewModel.setId(1L);

        UserWithClanModel userWithClanModel = new UserWithClanModel();
        userWithClanModel.setClan(clanViewModel);
        userWithClanModel.setUser(userViewModel);

        when(this.userGameInfoService.getWithClanByUserUsername("Test")).thenReturn(userWithClanModel);
        this.mvc.perform(get("/clans/my").principal(this.principal))
                .andExpect(status().isOk())
                .andExpect(model().attribute("canLeave", false));
    }

    @Test
    public void getMyClanPageWithPrincipalNotClanOwner_shouldReturnCanLeaveTrue() throws Exception {
        UserModel userModel = new UserModel();
        userModel.setId(1L);

        ClanViewModel clanViewModel = new ClanViewModel();
        clanViewModel.setId(1L);
        clanViewModel.setOwner(userModel);

        UserViewModel userViewModel = new UserViewModel();
        userViewModel.setId(2L);

        UserWithClanModel userWithClanModel = new UserWithClanModel();
        userWithClanModel.setClan(clanViewModel);
        userWithClanModel.setUser(userViewModel);

        when(this.userGameInfoService.getWithClanByUserUsername("Test")).thenReturn(userWithClanModel);
        this.mvc.perform(get("/clans/my").principal(this.principal))
                .andExpect(status().isOk())
                .andExpect(model().attribute("canLeave", true));
    }

    @Test
    public void getCreatePagePrincipalWithClan_shouldRedirect() throws Exception {
        UserGameInfoModel userGameInfoModel = new UserGameInfoModel();
        userGameInfoModel.setClan(new ClanModel());
        when(this.userGameInfoService.getByUserUsername("Test")).thenReturn(userGameInfoModel);
        this.mvc.perform(get("/clans/create").principal(this.principal))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/clans/my"));
    }

    @Test
    public void getCreatePagePrincipalWithoutClan_shouldReturnCreateClanPage() throws Exception {

        this.mvc.perform(get("/clans/create").principal(this.principal))
                .andExpect(status().isOk())
                .andExpect(model().attribute(Constants.VIEW, "/views/clan/create-clan"));
    }

    @Test
    public void createClanWithNoPrincipal_shouldReturnNoAccessView() throws Exception {
        when(this.principal.getName()).thenReturn(null);
        this.mvc.perform(post("/clans/create").principal(this.principal))
                .andExpect(status().isOk())
                .andExpect(model().attribute(Constants.VIEW, "/views/wrong-page"));
    }

    @Test
    public void createClanWithErrors_shouldReturnCreateClanView() throws Exception {
        when(this.userService.findByUsername("Test")).thenReturn(new UserModel());
        this.mvc.perform(post("/clans/create").param("name", "").principal(this.principal))
                .andExpect(status().isOk())
                 .andExpect(model().hasErrors())
                .andExpect(model().attribute(Constants.VIEW, "/views/clan/create-clan"));
    }

    @Test
    public void createClan_shouldRedirect() throws Exception {
        when(this.userService.findByUsername("Test")).thenReturn(new UserModel());
        this.mvc.perform(post("/clans/create").param("name", "Name").principal(this.principal))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/clans/my"));
    }

    @Test
    public void joinClanWithoutPrincipal_shouldRedirect() throws Exception {
        ClanModel clanModel = new ClanModel();
        clanModel.setId(1L);
        when(this.clanService.getClanById(1L)).thenReturn(clanModel);
        when(this.principal.getName()).thenReturn(null);
        this.mvc.perform(get("/clans/join/1").principal(this.principal))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void joinClanWithPrincipal_shouldRedirect() throws Exception {
        ClanModel clanModel = new ClanModel();
        clanModel.setId(1L);
        when(this.clanService.getClanById(1L)).thenReturn(clanModel);
        when(this.userService.findByUsername("Test")).thenReturn(new UserModel());
        this.mvc.perform(get("/clans/join/1").principal(this.principal))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/clans/my"));
    }

    @Test
    public void joinClanWithWrongId_shouldRedirect() throws Exception {
        this.mvc.perform(get("/clans/join/1").principal(this.principal))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/clans/all"));
    }

    @Test
    public void leaveClanWithoutPrincipal_shouldRedirect() throws Exception{
        when(this.principal.getName()).thenReturn(null);
        this.mvc.perform(get("/clans/leave").principal(this.principal))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void leaveClan_shouldRedirect() throws Exception{
        when(this.userService.findByUsername("Test")).thenReturn(new UserModel());
        this.mvc.perform(get("/clans/leave").principal(this.principal))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/clans/my"));
    }



}