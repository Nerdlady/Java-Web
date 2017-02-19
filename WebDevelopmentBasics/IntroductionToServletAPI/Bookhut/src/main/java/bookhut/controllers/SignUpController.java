package bookhut.controllers;

import bookhut.models.binding.LoginModel;
import bookhut.services.UserService;
import bookhut.servicesImpl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signup")
public class SignUpController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/signup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginModel loginModel = null;
        String signUp = req.getParameter("signup");
        if (signUp != null) {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            loginModel = new LoginModel(username, password);
            UserService userService = new UserServiceImpl();
            userService.createUser(loginModel);
            resp.sendRedirect("/signin");
        }

        if (loginModel == null){
            resp.sendRedirect("/signup");
        }
    }
}
