package softuni.controller;

import mvcFramework.annotations.controller.Controller;
import mvcFramework.annotations.request.GetMapping;
import mvcFramework.models.Model;
import softuni.constants.Constants;
import softuni.dtos.ArticleDto;
import softuni.services.ArticleService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
@Controller
public class HomeController {

    @Inject
    private ArticleService articleService;

    @GetMapping("/")
    public String details(Model model) {
        List<ArticleDto> articleDtos = this.articleService.getAll();
        model.addAttribute(Constants.TITLE, Constants.BLOG_TITLE);
        model.addAttribute("articles",articleDtos);
        model.addAttribute(Constants.VIEW, "home/index.jsp");
        return Constants.BASE_LAYOUT;
    }

}
