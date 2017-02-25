package controller;


import dtos.KnifeDto;
import dtos.MessageDto;
import dtos.OrderDto;
import dtos.UserDto;
import enums.Status;
import mvcFramework.annotations.controller.Controller;
import mvcFramework.annotations.parameters.GetCookie;
import mvcFramework.annotations.parameters.ModelAttribute;
import mvcFramework.annotations.parameters.PathVariable;
import mvcFramework.annotations.parameters.RequestParam;
import mvcFramework.annotations.request.GetMapping;
import mvcFramework.annotations.request.PostMapping;
import mvcFramework.models.Model;
import services.KnifeService;
import services.MessageService;
import services.OrderService;
import services.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Stateless
@Controller
public class HomeController {
    @Inject
    private KnifeService knifeService;
    @Inject
    private MessageService messageService;
    @Inject
    private OrderService orderService;
    @Inject
    private UserService userService;


    @GetMapping("/")
    public String home() {
        return "redirect:/home/index";
    }

    @GetMapping("/home/index")
    public String index(@GetCookie("theme") Cookie cookie, Model model) {
        this.checkCookie(cookie, model);
        return "home";
    }

    @GetMapping("/home/about")
    public String boot(@GetCookie("theme") Cookie cookie, Model model) {
        this.checkCookie(cookie, model);
        return "about";
    }

    @GetMapping("/home/products")
    public String products(@RequestParam("search_btn") String searchBtn,
                           @RequestParam("search") String search,
                           @GetCookie("theme") Cookie cookie,
                           Model model) {
        this.checkCookie(cookie, model);
        List<KnifeDto> knifeDtoList;
        if (searchBtn == null) {
            knifeDtoList = this.knifeService.getAllKnives();
        } else {
            knifeDtoList = this.knifeService.getAllWithNameContains(search);
        }

        model.addAttribute("knives", knifeDtoList);
        return "products";
    }

    @GetMapping("/home/contacts")
    public String contacts(@GetCookie("theme") Cookie cookie, Model model) {
        this.checkCookie(cookie, model);
        return "contacts";
    }

    @PostMapping("/home/contacts")
    public String contactsPost(@ModelAttribute() MessageDto messageDto) {
        this.messageService.save(messageDto);
        return "redirect:contacts";
    }

    @GetMapping("/home/buy/{id}")
    public String buyProduct(@GetCookie("theme") Cookie cookie, Model model) {
        this.checkCookie(cookie, model);
        return "buy";
    }

    @PostMapping("/home/buy/{id}")
    public String buyProduct(@ModelAttribute() OrderDto orderDto,
                             @RequestParam("type") String type,
                             @PathVariable("id") Long id) {
        KnifeDto knifeDto = this.knifeService.findById(id);
        orderDto.setProduct(knifeDto);
        orderDto.setStatus(Status.PENDING);
        this.orderService.addOrder(orderDto);
        return "redirect:/home/products";
    }

    @GetMapping("/home/light")
    public String lightTheme(HttpServletResponse response) {
        response.addCookie(new Cookie("theme", "light"));
        return "redirect:/home/index";
    }

    @GetMapping("/home/dark")
    public String darkTheme(HttpServletResponse response) {
        response.addCookie(new Cookie("theme", "dark"));
        return "redirect:/home/index";
    }

    @GetMapping("/home/login")
    public String login(@GetCookie("theme") Cookie cookie, Model model) {
        this.checkCookie(cookie, model);
        return "login";
    }

    @PostMapping("/home/login")
    public String login(@ModelAttribute() UserDto userDto, Model model, HttpSession httpSession) {
        UserDto userFromDb = this.userService.getByUsername(userDto.getUsername());
        if (userFromDb == null || !userDto.getPassword().equals(userFromDb.getPassword())) {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
        httpSession.setAttribute("user", userFromDb);

        return "redirect:/home/admin";
    }

    @GetMapping("/home/logout")
    public String logout(HttpSession httpSession) {
        httpSession.setAttribute("user", null);
        return "redirect:/home/index";
    }

    private void checkCookie(Cookie cookie, Model model) {
        if (cookie.getName().equals("theme")) {
            model.addAttribute("theme", cookie.getValue());
        }
    }
}
