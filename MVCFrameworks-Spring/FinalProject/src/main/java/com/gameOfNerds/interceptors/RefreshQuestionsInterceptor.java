package com.gameOfNerds.interceptors;

import com.gameOfNerds.areas.question.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;

@Component
public class RefreshQuestionsInterceptor extends HandlerInterceptorAdapter {
    private static long THIRTY_MINUTES = 30;
    private Calendar previousTime;
    private final QuestionService questionService;

    @Autowired
    public RefreshQuestionsInterceptor(QuestionService questionService) {
        this.questionService = questionService;
        this.previousTime = Calendar.getInstance();
        this.previousTime.setTime(new Date());
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        if (calendar.getTimeInMillis() >  this.previousTime.getTimeInMillis()){
            this.questionService.refreshQuestions();
            System.out.println("refresh " + new Date());
            calendar.add(Calendar.MINUTE,1);
            previousTime = calendar;
        }
        return super.preHandle(request, response, handler);
    }
}
