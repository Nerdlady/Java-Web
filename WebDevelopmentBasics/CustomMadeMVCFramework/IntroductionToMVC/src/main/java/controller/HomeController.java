package controller;


import dtos.KnifeDto;
import dtos.MessageDto;
import dtos.OrderDto;
import enums.OrderType;
import mvcFramework.annotations.controller.Controller;
import mvcFramework.annotations.parameters.PathVariable;
import mvcFramework.annotations.parameters.RequestParam;
import mvcFramework.annotations.request.GetMapping;
import mvcFramework.annotations.request.PostMapping;
import mvcFramework.models.Model;
import services.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class HomeController {
    private KnifeService knifeService;
    private MessageService messageService;
    private OrderService orderService;

    public HomeController() {
        this.knifeService = new KnifeServiceImpl();
        this.messageService = new MessageServiceImpl();
        this.orderService = new OrderServiceImpl();
        this.fillData();
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/home/index";
    }

    @GetMapping("/home/index")
    public String index() {
        return "home";
    }

    @GetMapping("/home/about")
    public String boot() {
        return "about";
    }

    @GetMapping("/home/products")
    public String products(@RequestParam("search_btn") String searchBtn, @RequestParam("search") String search, @RequestParam("buy") String buy, Model model) {
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
    public String contacts() {
        return "contacts";
    }

    @PostMapping("/home/contacts")
    public String contactsPost(@RequestParam("email") String sender, @RequestParam("subject") String subject, @RequestParam("message") String message) {
        MessageDto messageDto = new MessageDto(sender, subject, message);
        this.messageService.save(messageDto);
        System.out.println(messageDto.getSenderEmail());
        return "redirect:contacts";
    }

    @GetMapping("/home/buy/{id}")
    public String buyProduct() {
        return "buy";
    }

    @PostMapping("/home/buy/{id}")
    public String buyProduct(@RequestParam("name") String name,
                             @RequestParam("number") String number,
                             @RequestParam("address") String address,
                             @RequestParam("type") String type,
                             @PathVariable("id") Long id) {
        OrderType orderType = OrderType.valueOf(type.toUpperCase());
        KnifeDto knifeDto = this.knifeService.findById(id);
        OrderDto orderDto = new OrderDto(name,number,address,orderType,knifeDto);
        this.orderService.addOrder(orderDto);
        System.out.println(orderDto.getBuyerAddress());
        System.out.println(orderDto.getProduct().getName());
        return "redirect:/home/products";
    }

    private void fillData() {
        KnifeDto knifeDto = new KnifeDto("Sharp 300", new BigDecimal("140.00"), "http://thebladeguru.com/wp-content/uploads/2016/02/image-of-HK-Entourage-Black-Automatic-Knife-300x150.png");
        this.knifeService.save(knifeDto);
        KnifeDto knifeDto2 = new KnifeDto("Sharp 4", new BigDecimal("99.00"), "http://www.ukepics.com/wp-content/uploads/2016/08/Granny-300x150.jpg");
        this.knifeService.save(knifeDto2);
        KnifeDto knifeDto3 = new KnifeDto("Sharp Ultimate", new BigDecimal("450.00"), "http://www.knifeup.com/wp-content/uploads/2012/12/kabar-bk9-stock-300x150.jpg");
        this.knifeService.save(knifeDto3);
    }
}
