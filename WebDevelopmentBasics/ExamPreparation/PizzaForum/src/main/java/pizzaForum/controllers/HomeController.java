package pizzaForum.controllers;

import mvcFramework.annotations.controller.Controller;
import mvcFramework.annotations.request.GetMapping;
import mvcFramework.models.Model;
import pizzaForum.constants.Constants;
import pizzaForum.models.viewModels.ViewTopic;
import pizzaForum.services.TopicService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
@Controller
public class HomeController {
    @Inject
    private TopicService topicService;

    @GetMapping("/")
    public String getHomePage(Model model){
        List<ViewTopic> viewTopics = this.topicService.getAllTopics();
        model.addAttribute("topics",viewTopics);
        model.addAttribute("view","/topics/topic-template.jsp");
        return Constants.BASE_LAYOUT;
    }
}
