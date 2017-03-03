package softuni.controller;

import mvcFramework.annotations.controller.Controller;
import mvcFramework.annotations.parameters.ModelAttribute;
import mvcFramework.annotations.request.GetMapping;
import mvcFramework.annotations.request.PostMapping;
import mvcFramework.models.Model;
import softuni.constants.Constants;
import softuni.dtos.UserDto;
import softuni.services.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

@Stateless
@Controller
public class LoginController {

    @Inject
    private UserService userService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Softuni Blog");
        model.addAttribute("view", "user/login.jsp");
        return Constants.BASE_LAYOUT;
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.setAttribute(Constants.USER_SESSION, null);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute() UserDto userDto, Model model, HttpSession httpSession) {
        List<String> errors = this.setErrors(userDto);
        if (!errors.isEmpty()) {
            model.addAttribute(Constants.TITLE, Constants.BLOG_TITLE);
            model.addAttribute(Constants.ERRORS, errors);
            model.addAttribute(Constants.VIEW, "user/login.jsp");
            return Constants.BASE_LAYOUT;
        }
        UserDto userDtoFromDb = this.userService.getByEmail(userDto.getEmail());
        httpSession.setAttribute(Constants.USER_SESSION, userDtoFromDb);
        return "redirect:/";
    }

    @GetMapping("/profile/")
    public String profile(Model model,HttpSession httpSession){
        UserDto userDto= (UserDto) httpSession.getAttribute(Constants.USER_SESSION);
        if (userDto == null){
            return "redirect:/";
        }
        model.addAttribute(Constants.TITLE, Constants.BLOG_TITLE);
        model.addAttribute("user",userDto);
        model.addAttribute(Constants.VIEW, "user/profile.jsp");
        return Constants.BASE_LAYOUT;
    }

    private List<String> setErrors(UserDto userDto) {
        List<String> errors = new LinkedList<>();
        Pattern emailPattern = Pattern.compile("^[a-zA-z][A-Za-z0-9+_.-]*@(.+)$", Pattern.CASE_INSENSITIVE);

        if (userDto.getEmail() == null) {
            errors.add(Constants.EMAIL_NOT_NULL);
            return errors;
        } else if (!emailPattern.matcher(userDto.getEmail()).find()) {
            errors.add(Constants.INVALID_EMAIL);
        }

        UserDto userDtoFromDb = this.userService.getByEmail(userDto.getEmail());

        if (userDtoFromDb == null) {
            errors.add(Constants.WRONG_EMAIL);
            return errors;
        }
        if (userDto.getPassword() == null) {
            errors.add(Constants.PASSWORD_NOT_NULL);
        } else if (!userDto.getPassword().equals(userDtoFromDb.getPassword())) {
            errors.add(Constants.WRONG_PASSWORDS);
        }

        return errors;
    }
}
