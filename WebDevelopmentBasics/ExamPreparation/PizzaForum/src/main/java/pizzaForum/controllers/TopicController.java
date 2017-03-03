package pizzaForum.controllers;

import mvcFramework.annotations.controller.Controller;
import mvcFramework.annotations.parameters.ModelAttribute;
import mvcFramework.annotations.parameters.PathVariable;
import mvcFramework.annotations.parameters.RequestParam;
import mvcFramework.annotations.request.GetMapping;
import mvcFramework.annotations.request.PostMapping;
import mvcFramework.models.Model;
import pizzaForum.constants.Constants;
import pizzaForum.models.bindingModels.category.CategoryModel;
import pizzaForum.models.bindingModels.reply.NewReply;
import pizzaForum.models.bindingModels.topic.NewTopic;
import pizzaForum.models.bindingModels.user.LoggedUser;
import pizzaForum.models.viewModels.ViewCategory;
import pizzaForum.models.viewModels.ViewReply;
import pizzaForum.models.viewModels.ViewTopic;
import pizzaForum.services.CategoryService;
import pizzaForum.services.ReplyService;
import pizzaForum.services.TopicService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Stateless
@Controller
public class TopicController {

    @Inject
    private CategoryService categoryService;

    @Inject
    private TopicService topicService;

    @Inject
    private ReplyService replyService;

    @GetMapping("/topics/new")
    public String getNewTopicPage(Model model) {
        List<ViewCategory> categories = this.categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("view", "/topics/topic-new.jsp");
        return Constants.BASE_LAYOUT;
    }

    @PostMapping("/topics/new")
    public String addTopic(@ModelAttribute() NewTopic topic, @RequestParam("categoryName") String categoryName, HttpSession httpSession) {
        CategoryModel categoryModel = this.categoryService.findByName(categoryName);
        LoggedUser user = (LoggedUser) httpSession.getAttribute("user");
        topic.setCategory(categoryModel);
        topic.setAuthor(user);
        topic.setPublishDate(new Date());
        this.topicService.save(topic);
        return "redirect:/";
    }

    @GetMapping("/topics/details/{id}")
    public String getTopicDetailsPage(Model model, @PathVariable("id") Long id) {
        ViewTopic viewTopic = this.topicService.findById(id);
        List<ViewReply> viewReplies = this.replyService.getAllReplies(id);
        model.addAttribute("replies",viewReplies);
        model.addAttribute("topic", viewTopic);
        model.addAttribute("view", "/topics/topic-details.jsp");
        return Constants.BASE_LAYOUT;
    }

    @PostMapping("/topics/details/{id}")
    public String addReplyToTopic(@ModelAttribute() NewReply reply, @PathVariable("id") Long id, HttpSession httpSession) {
        LoggedUser user = (LoggedUser) httpSession.getAttribute("user");
        ViewTopic viewTopic = this.topicService.findById(id);
        reply.setAuthor(user);
        reply.setPublishDate(new Date());
        reply.setTopic(viewTopic);
        this.replyService.save(reply);
        return "redirect:/topics/details/" + id;
    }


    @GetMapping("/topics/delete/{id}")
    public String deleteTopic(@PathVariable("id") Long id){
        this.topicService.delete(id);
        return "redirect:/";
    }
}
