package com.filter;


import com.entities.enums.Role;
import com.models.bindingModels.user.LoggedUser;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpSession httpSession = ((HttpServletRequest) req).getSession();
        LoggedUser loggedUser = (LoggedUser) httpSession.getAttribute("user");
        if (loggedUser == null || loggedUser.getRole() != Role.ADMIN){
            ((HttpServletResponse) resp).sendRedirect("/login");
            return;
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
