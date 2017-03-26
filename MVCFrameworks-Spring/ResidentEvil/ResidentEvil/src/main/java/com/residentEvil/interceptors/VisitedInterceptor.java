package com.residentEvil.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class VisitedInterceptor extends HandlerInterceptorAdapter {
    private  int times = 0;
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

           this.times++;
           request.setAttribute("times",times );
           modelAndView.addObject("visited",times );

    }
}
