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
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

@Stateless
@Controller
public class RegisterController {

    @Inject
    private UserService userService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("title", "Softuni Blog");
        model.addAttribute("view", "user/register.jsp");
        return Constants.BASE_LAYOUT;
    }

    @PostMapping("/register")
    public String register(@ModelAttribute() UserDto userDto,Model model) {
        List<String> errors = this.setErrors(userDto);
        if (!errors.isEmpty()){
            model.addAttribute(Constants.ERRORS,errors);
            model.addAttribute(Constants.TITLE, Constants.BLOG_TITLE);
            model.addAttribute(Constants.VIEW, "user/register.jsp");
            return Constants.BASE_LAYOUT;
        }
        this.userService.save(userDto);
        return "redirect:/login";
    }

    private List<String> setErrors(UserDto userDto) {
        List<String> errors = new LinkedList<>();
        Pattern emailPattern = Pattern.compile("^[a-zA-z][A-Za-z0-9+_.-]*@(.+)$",Pattern.CASE_INSENSITIVE);

        if (userDto.getEmail() == null) {
            errors.add(Constants.EMAIL_NOT_NULL);
        } else if (!emailPattern.matcher(userDto.getEmail()).find()){
            errors.add(Constants.INVALID_EMAIL);
        } else if (this.userService.getByEmail(userDto.getEmail()) != null){
            errors.add(Constants.EMAIL_ALREADY_REGISTER);
            return errors;
        }

        Pattern namePattern = Pattern.compile("^([A-Z\\s+])+$",Pattern.CASE_INSENSITIVE);
        if (userDto.getName() == null){
            errors.add(Constants.NAME_NOT_NULL);
        } else if (userDto.getName().length() < Constants.NAME_MIN_LENGTH || userDto.getName().length() > Constants.NAME_MAX_LENGTH){
            errors.add(Constants.INVALID_NAME_LENGTH);
        } else if (!namePattern.matcher(userDto.getName()).find()){
            errors.add(Constants.INVALID_NAME);
        }

        if (userDto.getPassword() == null){
            errors.add(Constants.PASSWORD_NOT_NULL);
        } else  if (userDto.getPassword().length() < Constants.PASSWORD_MIN_LENGTH || userDto.getPassword().length() > Constants.PASSWORD_MAX_LENGTH){
            errors.add(Constants.INVALID_PASSWORD_LENGTH);
        }

        if (userDto.getConfirmPassword() == null){
            errors.add(Constants.CONFIRM_PASSWORD_NOT_NULL);
        } else if (!userDto.getPassword().equals(userDto.getConfirmPassword())){
            errors.add(Constants.PASSWORDS_MISMATCH);
        }

        return errors;
    }
}
