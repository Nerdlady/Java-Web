package pizzaForum.controllers;

import mvcFramework.annotations.controller.Controller;
import mvcFramework.annotations.parameters.ModelAttribute;
import mvcFramework.annotations.parameters.PathVariable;
import mvcFramework.annotations.request.GetMapping;
import mvcFramework.annotations.request.PostMapping;
import mvcFramework.models.Model;
import pizzaForum.constants.Constants;
import pizzaForum.models.bindingModels.user.LoggedUser;
import pizzaForum.models.bindingModels.user.LoginUser;
import pizzaForum.models.bindingModels.user.RegisterUser;
import pizzaForum.models.viewModels.UserView;
import pizzaForum.services.TopicService;
import pizzaForum.services.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Controller
@Stateless
public class UserController {
    @Inject
    private UserService userService;

    @Inject
    private TopicService topicService;

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("view", "/user/register.jsp");
        return Constants.BASE_LAYOUT;
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute() RegisterUser registerUser, Model model) {
        List<String> errors = this.setRegisterErrors(registerUser);
        if (errors.size() > 0) {
            model.addAttribute("view", "/user/register.jsp");
            model.addAttribute("errors", errors);
            return Constants.BASE_LAYOUT;
        }
        this.userService.save(registerUser);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("view", "/user/login.jsp");
        return Constants.BASE_LAYOUT;
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute() LoginUser loginUser, Model model, HttpSession httpSession) {
        List<String> errors = this.setLoginErrors(loginUser);
        if (errors.size() > 0) {
            model.addAttribute("view", "/user/login.jsp");
            model.addAttribute("errors", errors);
            return Constants.BASE_LAYOUT;
        }

        LoggedUser loggedUser = this.userService.getByUsernameOrEmailAndPassword(loginUser.getUsername(), loginUser.getPassword());
        httpSession.setAttribute("user", loggedUser);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }

    @GetMapping("/profile/{id}")
    public String getProfilePage(Model model, @PathVariable("id") Long id) {
        UserView userView = this.userService.getById(id);
        if (userView == null){
            return "redirect:/";
        }


        model.addAttribute("userProfile",userView);
        model.addAttribute("view", "/user/profile.jsp");
        return Constants.BASE_LAYOUT;
    }


    private List<String> setRegisterErrors(RegisterUser user) {
        List<String> errors = new ArrayList<>();


        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            errors.add(Constants.EMAIL_NOT_NULL);
        } else if (!user.getEmail().contains("@")) {
            errors.add(Constants.INVALID_EMAIL);
        }

        Pattern namePattern = Pattern.compile("^([a-z0-9])+$", Pattern.CASE_INSENSITIVE);
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            errors.add(Constants.NAME_NOT_NULL);
        } else if (user.getUsername().length() < Constants.NAME_MIN_LENGTH) {
            errors.add(Constants.INVALID_NAME_LENGTH);
        } else if (!namePattern.matcher(user.getUsername()).find()) {
            errors.add(Constants.INVALID_NAME);
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            errors.add(Constants.PASSWORD_NOT_NULL);
        } else if (user.getPassword().length() < Constants.PASSWORD_LENGTH || user.getPassword().length() > Constants.PASSWORD_LENGTH) {
            errors.add(Constants.INVALID_PASSWORD_LENGTH);
        }

        if (user.getConfirmPassword() == null || user.getConfirmPassword().isEmpty()) {
            errors.add(Constants.CONFIRM_PASSWORD_NOT_NULL);
        } else if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.add(Constants.PASSWORDS_MISMATCH);
        }


        return errors;
    }

    private List<String> setLoginErrors(LoginUser user) {
        List<String> errors = new ArrayList<>();

        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            errors.add(Constants.EMAIL_USERNAME_NOT_NULL);
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            errors.add(Constants.PASSWORD_NOT_NULL);
        }

        if (errors.size() > 0) {
            return errors;
        }

        LoggedUser loggedUser = this.userService.getByUsernameOrEmailAndPassword(user.getUsername(), user.getPassword());

        if (loggedUser == null) {
            errors.add(Constants.WRONG_USERNAME_OR_PASSWORD);
        }

        return errors;
    }
}
