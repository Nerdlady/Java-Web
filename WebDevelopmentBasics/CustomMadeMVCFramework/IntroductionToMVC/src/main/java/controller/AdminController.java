package controller;

import dtos.KnifeDto;
import dtos.MessageDto;
import dtos.OrderDto;
import mvcFramework.annotations.controller.Controller;
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
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Stateless
@Controller
public class AdminController {
    @Inject
    private KnifeService knifeService;
    @Inject
    private MessageService messageService;
    @Inject
    private OrderService orderService;
    @Inject
    private UserService userService;

    @GetMapping("/home/admin")
    public String adminHome(HttpSession httpSession) {
        if (httpSession.getAttribute("user") != null) {
            this.setAdminMenuItems(httpSession);
        }
        return "redirect:/home/index";
    }

    @GetMapping("/home/admin/add")
    public String addProduct(HttpSession httpSession) {
        if (httpSession.getAttribute("user") == null) {
            return "redirect:/home/index";
        }
        return "/admin/products/add";
    }

    @PostMapping("/home/admin/add")
    public String addProduct(HttpSession httpSession, @ModelAttribute() KnifeDto knifeDto) {
        if (httpSession.getAttribute("user") != null) {
            this.setAdminMenuItems(httpSession);
        }
        this.knifeService.save(knifeDto);
        return "redirect:/home/products";
    }

    @GetMapping("/home/admin/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("user") == null) {
            return "redirect:/home/index";
        }
        KnifeDto knifeDto = this.knifeService.findById(id);
        model.addAttribute("knife", knifeDto);
        return "/admin/products/edit";
    }

    @PostMapping("/home/admin/edit/{id}")
    public String edit(HttpSession httpSession, @PathVariable("id") Long id, @ModelAttribute() KnifeDto knifeDto) {
        if (httpSession.getAttribute("user") != null) {
            this.setAdminMenuItems(httpSession);
        }
        this.knifeService.update(knifeDto, id);
        return "redirect:/home/products";
    }

    @GetMapping("/home/admin/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("user") == null) {
            return "redirect:/home/index";
        }
        KnifeDto knifeDto = this.knifeService.findById(id);
        model.addAttribute("knife", knifeDto);
        return "/admin/products/delete";
    }

    @PostMapping("/home/admin/delete/{id}")
    public String delete(HttpSession httpSession, @RequestParam("yes") String yesButton, @PathVariable("id") Long id) {
        if (httpSession.getAttribute("user") != null) {
            this.setAdminMenuItems(httpSession);
        }
        if (yesButton != null) {
            this.knifeService.delete(id);
        }
        return "redirect:/home/products";
    }

    @GetMapping("/home/admin/messages")
    public String messages(HttpSession httpSession, Model model) {
        if (httpSession.getAttribute("user") == null) {
            return "redirect:/home/index";
        }
        List<MessageDto> messageDto = this.messageService.getAllMessages();
        model.addAttribute("messages", messageDto);
        return "/admin/messages/messages";
    }

    @GetMapping("/home/admin/messages/view/{id}")
    public String messageView(HttpSession httpSession, @PathVariable("id") Long id, Model model) {
        MessageDto messageDto = this.messageService.getById(id);
        model.addAttribute("message", messageDto);
        return "/admin/messages/view";
    }

    @GetMapping("/home/admin/messages/reply/{id}")
    public String replyMessage(HttpSession httpSession, @PathVariable("id") Long id, Model model) {
        if (httpSession.getAttribute("user") == null) {
            return "redirect:/home/index";
        }
        MessageDto messageDto = this.messageService.getById(id);
        model.addAttribute("message", messageDto);
        return "/admin/messages/reply";
    }

    @PostMapping("/home/admin/messages/reply/{id}")
    public String replyMessage(HttpSession httpSession) {
        if (httpSession.getAttribute("user") != null) {
            this.setAdminMenuItems(httpSession);
        }
        return "redirect:/home/admin/messages";
    }

    @GetMapping("/home/admin/messages/delete/{id}")
    public String deleteMessage(HttpSession httpSession, @PathVariable("id") Long id) {
        if (httpSession.getAttribute("user") == null) {
            return "redirect:/home/index";
        }
        this.messageService.delete(id);
        return "redirect:/home/admin/messages";
    }

    @GetMapping("/home/admin/orders")
    public String orders(HttpSession httpSession, Model model) {
        if (httpSession.getAttribute("user") == null) {
            return "redirect:/home/index";
        }

        List<OrderDto> orderDtos = this.orderService.getAllOrders();
        model.addAttribute("orders", orderDtos);
        return "/admin/orders/orders";
    }

    @GetMapping("/home/admin/orders/edit/{id}")
    public String orderEdit(HttpSession httpSession, @PathVariable("id") Long id, Model model) {
        if (httpSession.getAttribute("user") == null) {
            return "redirect:/home/index";
        }
        OrderDto order = this.orderService.getById(id);
        model.addAttribute("order", order);
        return "/admin/orders/edit";
    }

    @PostMapping("/home/admin/orders/edit/{id}")
    public String orderEdit(HttpSession httpSession, @PathVariable("id") Long id, @ModelAttribute() OrderDto orderDto) {
        if (httpSession.getAttribute("user") != null) {
            this.setAdminMenuItems(httpSession);
        }
        this.orderService.update(orderDto, id);
        return "redirect:/home/admin/orders";
    }


    private void setAdminMenuItems(HttpSession httpSession) {
        Map<String, String> menuItems = new LinkedHashMap<>();
        menuItems.put("/home/admin/add", "Add Product");
        menuItems.put("/home/admin/messages", "Messages");
        menuItems.put("/home/admin/orders", "Orders");
        httpSession.setAttribute("menuItems", menuItems);

    }
}
