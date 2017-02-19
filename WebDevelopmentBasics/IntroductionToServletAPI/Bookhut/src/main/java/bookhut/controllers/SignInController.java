package bookhut.controllers;

import bookhut.Config;
import bookhut.models.binding.LoginModel;
import bookhut.services.UserService;
import bookhut.servicesImpl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/signin")
public class SignInController extends HttpServlet {
    private UserService userService;

    public SignInController() {
        this.userService = new UserServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginModel loginModel = null;
        String signIn = request.getParameter("signin");

        if (signIn != null){
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            loginModel = this.userService.findByUsernameAndPassword(username,password);
        }

        if (loginModel != null){
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute(Config.LOGIN_MODEL,loginModel);
            response.sendRedirect("/");
        } else {
            response.sendRedirect("/signin");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/signin.jsp").forward(request,response);
    }
}
