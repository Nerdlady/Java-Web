package com.gameOfNerds.areas.user.controllers;

import com.gameOfNerds.areas.user.models.viewModels.UserGameInfoViewModel;
import com.gameOfNerds.areas.user.models.viewModels.UserViewModel;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UsersController.class)
@ActiveProfiles("test")
public class UsersControllerTest {

    @Autowired
    private WebApplicationContext wac;


    private MockMvc mvc;

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
        //Arrange
        mvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())
                .build();
        UserViewModel userViewModel = new UserViewModel();
        userViewModel.setUsername("Test");
        userViewModel.setId(1L);

        UserGameInfoViewModel userGameInfoViewModel = new UserGameInfoViewModel();
        userGameInfoViewModel.setId(1L);
        userGameInfoViewModel.setUser(userViewModel);
        userGameInfoViewModel.setScore(15.5);

        when(this.principal.getName()).thenReturn("Test");

        when(this.userService.getById(1L)).thenReturn(userViewModel);
        when(this.userGameInfoService.getViewByUserId(1L)).thenReturn(userGameInfoViewModel);
        when(this.userGameInfoService.getViewByUserUsername("Test")).thenReturn(userGameInfoViewModel);

    }

    @Test
    public void getChallengePageWithExistingId_shouldReturnChallengeView() throws Exception {
        when(this.principal.getName()).thenReturn("Ivan");
        this.mvc.perform(get("/users/challenge/1").principal(principal))
                .andExpect(status().isOk())
                .andExpect(model().attribute(Constants.VIEW, "/views/user/challenge"))
                .andExpect(model().attribute("challengedUser", hasProperty("id", is(1L))));
    }

    @Test
    public void getChallengePageWithYourself_shouldReturnChallengeYourselfView() throws Exception {
        this.mvc.perform(get("/users/challenge/1").principal(principal))
                .andExpect(status().isOk())
                .andExpect(model().attribute(Constants.VIEW, "/views/user/fight/challenge-yourself"));
    }

    @Test
    public void getChallengePageWithNonExistingId_shouldReturnNoAccessView() throws Exception {
        when(this.principal.getName()).thenReturn("Ivan");
        this.mvc.perform(get("/users/challenge/2").principal(principal))
                .andExpect(status().isOk())
                .andExpect(model().attribute(Constants.VIEW, "/views/wrong-page"));
    }

    @Test
    public void getProfilePageWithExistingId_shouldReturnProfileView() throws Exception {
        this.mvc.perform(get("/users/profile/1").principal(this.principal))
                .andExpect(status().isOk())
                .andExpect(model().attribute(Constants.VIEW, "/views/user/profile"))
                .andExpect(model().attribute("user", hasProperty("id", is(1L))));
    }

    @Test
    public void getProfilePageWithNonExistingId_shouldReturnNoAccessView() throws Exception {
        this.mvc.perform(get("/users/profile/2").principal(this.principal))
                .andExpect(status().isOk())
                .andExpect(model().attribute(Constants.VIEW, "/views/wrong-page"));
    }

    @Test
    public void getUserProfilePage_shouldReturnProfileView() throws Exception {
        this.mvc.perform(get("/users/profile").principal(this.principal))
                .andExpect(status().isOk())
                .andExpect(model().attribute(Constants.VIEW, "/views/user/profile"))
                .andExpect(model().attribute("user", hasProperty("id", is(1L))));
    }

}