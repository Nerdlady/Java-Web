package com.gameOfNerds.areas.question.controllers;

import com.gameOfNerds.areas.question.models.bindingModels.AddQuestionModel;
import com.gameOfNerds.areas.question.models.viewModels.QuestionViewModel;
import com.gameOfNerds.areas.question.services.QuestionService;
import com.gameOfNerds.areas.user.services.UserGameInfoService;
import com.gameOfNerds.areas.user.services.UserService;
import com.gameOfNerds.areas.user.services.UsersFightService;
import com.gameOfNerds.constants.Constants;
import com.gameOfNerds.interceptors.RefreshQuestionsInterceptor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(QuestionController.class)
@ActiveProfiles("test")
public class QuestionControllerTest {
    private static final Long QUESTION_ID = 1L;
    public static final String QUESTION_CONTEXT = "Question context";
    public static final String RIGHT_ANSWER = "Right answer";
    public static final String WRONG_ANSWER = "Wrong answer one";
    public static final String WRONG_ANSWER_TWO = "Wrong answer two";
    public static final String WRONG_ANSWER_THREE = "Wrong answer three";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private QuestionService questionService;
    @MockBean
    private UserService userService;
    @MockBean
    private RefreshQuestionsInterceptor refreshQuestionsInterceptor;
    @MockBean
    private UsersFightService usersFightService;
    @MockBean
    private UserGameInfoService userGameInfoService;

    @Before
    public void setUp() throws Exception {
        //Arrange
        QuestionViewModel questionViewModel = new QuestionViewModel();
        questionViewModel.setContext(QUESTION_CONTEXT);
        questionViewModel.setRightAnswer(RIGHT_ANSWER);
        questionViewModel.setWrongAnswerOne(WRONG_ANSWER);
        questionViewModel.setWrongAnswerTwo(WRONG_ANSWER_TWO);
        questionViewModel.setWrongAnswerThree(WRONG_ANSWER_THREE);
        questionViewModel.setId(QUESTION_ID);
        when(this.questionService.getOneQuestion()).thenReturn(questionViewModel);
        when(this.questionService.getOneQuestionById(QUESTION_ID)).thenReturn(questionViewModel);
    }

    @Test
    public void getAddQuestionPage_shouldPass() throws Exception {
        this.mvc.perform(get("/questions/add"))
                .andExpect(status().isOk())
                .andExpect(view().name(Constants.LAYOUT));
    }

    @Test
    public void addQuestion_shouldPass() throws Exception {

        this.mvc.perform(post("/questions/add")
                .param("context", QUESTION_CONTEXT)
                .param("rightAnswer", RIGHT_ANSWER)
                .param("wrongAnswerOne", WRONG_ANSWER)
                .param("wrongAnswerTwo", WRONG_ANSWER_TWO)
                .param("wrongAnswerThree", WRONG_ANSWER_THREE))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"))
                .andExpect(redirectedUrl("/"));

        ArgumentCaptor<AddQuestionModel> captor = ArgumentCaptor.forClass(AddQuestionModel.class);
        verify(questionService).persist(captor.capture());
        AddQuestionModel bu = captor.getValue();
        assertEquals(QUESTION_CONTEXT, bu.getContext());
    }


    @Test
    public void addQuestionWithNoData_shouldReturnSamePage() throws Exception {
        this.mvc.perform(post("/questions/add"))
                .andExpect(status().isOk())
                .andExpect(view().name(Constants.LAYOUT))
                .andExpect(model().hasErrors());

    }

    @Test
    public void getPracticeQuestionPage_shouldPass() throws Exception {
        this.mvc.perform(get("/questions/practice"))
                .andExpect(status().isOk());
    }

    @Test
    public void getQuestion_shouldReturnRightJSON() throws Exception {
        this.mvc.perform(get("/questions/get"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(1)))
                .andExpect(jsonPath("context",is(QUESTION_CONTEXT)))
                .andExpect(jsonPath("rightAnswer",is(RIGHT_ANSWER)))
                .andExpect(jsonPath("wrongAnswerOne",is(WRONG_ANSWER)))
                .andExpect(jsonPath("wrongAnswerTwo",is(WRONG_ANSWER_TWO)))
                .andExpect(jsonPath("wrongAnswerThree",is(WRONG_ANSWER_THREE)));
    }

    @Test
    public void getQuestionByIdWithExistingId_shouldrReturnRightJSON() throws Exception{
        this.mvc.perform(get("/questions/get/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(1)))
                .andExpect(jsonPath("context",is(QUESTION_CONTEXT)))
                .andExpect(jsonPath("rightAnswer",is(RIGHT_ANSWER)))
                .andExpect(jsonPath("wrongAnswerOne",is(WRONG_ANSWER)))
                .andExpect(jsonPath("wrongAnswerTwo",is(WRONG_ANSWER_TWO)))
                .andExpect(jsonPath("wrongAnswerThree",is(WRONG_ANSWER_THREE)));
    }

    @Test
    public void getQuestionByIdWithNonExistingId_shouldReturnEmptyJSON() throws Exception{
        this.mvc.perform(get("/questions/get/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").doesNotExist());
    }


}