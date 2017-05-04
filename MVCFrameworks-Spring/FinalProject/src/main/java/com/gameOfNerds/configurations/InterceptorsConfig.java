package com.gameOfNerds.configurations;

import com.gameOfNerds.interceptors.RefreshQuestionsInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class InterceptorsConfig extends WebMvcConfigurerAdapter {
    private final RefreshQuestionsInterceptor refreshQuestionsInterceptor;

    @Autowired
    public InterceptorsConfig(RefreshQuestionsInterceptor refreshQuestionsInterceptor) {
        this.refreshQuestionsInterceptor = refreshQuestionsInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.refreshQuestionsInterceptor).addPathPatterns("/questions/practice","/users/fight/**");
    }
}
