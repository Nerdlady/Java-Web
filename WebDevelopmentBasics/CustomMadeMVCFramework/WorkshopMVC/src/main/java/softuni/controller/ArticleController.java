package softuni.controller;

import mvcFramework.annotations.controller.Controller;
import mvcFramework.annotations.parameters.ModelAttribute;
import mvcFramework.annotations.parameters.PathVariable;
import mvcFramework.annotations.request.GetMapping;
import mvcFramework.annotations.request.PostMapping;
import mvcFramework.models.Model;
import softuni.constants.Constants;
import softuni.dtos.ArticleDto;
import softuni.dtos.UserDto;
import softuni.services.ArticleService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Controller
public class ArticleController {

    @Inject
    private ArticleService articleService;

    @GetMapping("/articles/create")
    public String addArticle(Model model, HttpSession httpSession) {
        if (httpSession.getAttribute(Constants.USER_SESSION) == null) {
            return "redirect:/";
        }
        model.addAttribute(Constants.TITLE, Constants.BLOG_TITLE);
        model.addAttribute(Constants.VIEW, "article/add.jsp");
        return Constants.BASE_LAYOUT;
    }

    @PostMapping("/articles/create")
    public String addArticle(@ModelAttribute() ArticleDto articleDto, Model model, HttpSession httpSession) {
        if (httpSession.getAttribute(Constants.USER_SESSION) == null) {
            return "redirect:/";
        }

        List<String> errors = this.setErrors(articleDto);
        if (!errors.isEmpty()) {
            model.addAttribute(Constants.TITLE, Constants.BLOG_TITLE);
            model.addAttribute(Constants.ERRORS, errors);
            model.addAttribute(Constants.VIEW, "article/add.jsp");
            return Constants.BASE_LAYOUT;
        }

        UserDto userDto = (UserDto) httpSession.getAttribute(Constants.USER_SESSION);
        articleDto.setAuthor(userDto);
        this.articleService.save(articleDto);
        return "redirect:/";
    }

    @GetMapping("/articles/details/{id}")
    public String articleDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute(Constants.TITLE, Constants.BLOG_TITLE);
        ArticleDto articleDto = this.articleService.getById(id);
        model.addAttribute("article", articleDto);
        model.addAttribute(Constants.VIEW, "article/details.jsp");
        return Constants.BASE_LAYOUT;
    }

    @GetMapping("/articles/edit/{id}")
    public String editArticle(Model model, @PathVariable("id") Long id, HttpSession httpSession) {
        UserDto userDto = (UserDto) httpSession.getAttribute(Constants.USER_SESSION);
        ArticleDto articleDto = this.articleService.getById(id);
        if (userDto == null || articleDto == null || !articleDto.getAuthor().getEmail().equals(userDto.getEmail())) {
            return "redirect:/";
        }

        model.addAttribute(Constants.TITLE, Constants.BLOG_TITLE);
        model.addAttribute("article", articleDto);
        model.addAttribute(Constants.VIEW, "article/edit.jsp");
        return Constants.BASE_LAYOUT;
    }

    @PostMapping("/articles/edit/{id}")
    public String editArticle(Model model, @PathVariable("id") Long id, @ModelAttribute() ArticleDto articleDto, HttpSession httpSession) {
        UserDto userDto = (UserDto) httpSession.getAttribute(Constants.USER_SESSION);
        ArticleDto articleDtoDb = this.articleService.getById(id);
        if (userDto == null || articleDtoDb == null || !articleDtoDb.getAuthor().getEmail().equals(userDto.getEmail())) {
            return "redirect:/";
        }

        List<String> errors = this.setErrors(articleDto);
        if (!errors.isEmpty()) {
            ArticleDto articleDtoOld = this.articleService.getById(id);
            model.addAttribute(Constants.TITLE, Constants.BLOG_TITLE);
            model.addAttribute("article", articleDtoOld);
            model.addAttribute(Constants.ERRORS, errors);
            model.addAttribute(Constants.VIEW, "article/edit.jsp");
            return Constants.BASE_LAYOUT;
        }

        articleDto.setId(id);
        this.articleService.update(articleDto);

        return "redirect:/";
    }

    @GetMapping("/articles/delete/{id}")
    public String deleteArticle(Model model, @PathVariable("id") Long id, HttpSession httpSession) {
        UserDto userDto = (UserDto) httpSession.getAttribute(Constants.USER_SESSION);
        ArticleDto articleDtoDb = this.articleService.getById(id);
        if (userDto == null || articleDtoDb == null || !articleDtoDb.getAuthor().getEmail().equals(userDto.getEmail())) {
            return "redirect:/";
        }

        model.addAttribute(Constants.TITLE, Constants.BLOG_TITLE);
        ArticleDto articleDto = this.articleService.getById(id);
        model.addAttribute("article", articleDto);
        model.addAttribute(Constants.VIEW, "article/delete.jsp");
        return Constants.BASE_LAYOUT;
    }

    @PostMapping("/articles/delete/{id}")
    public String deleteArticlePost(Model model, @PathVariable("id") Long id, HttpSession httpSession) {
        UserDto userDto = (UserDto) httpSession.getAttribute(Constants.USER_SESSION);
        ArticleDto articleDtoDb = this.articleService.getById(id);
        if (userDto == null || articleDtoDb == null || !articleDtoDb.getAuthor().getEmail().equals(userDto.getEmail())) {
            return "redirect:/";
        }

        this.articleService.delete(id);
        return "redirect:/";
    }


    public List<String> setErrors(ArticleDto articleDto) {
        List<String> errors = new ArrayList<>();
        if (articleDto.getTitle() == null) {
            errors.add(Constants.ARTICLE_TITLE_NOT_NULL);
        } else if (articleDto.getTitle().length() < Constants.ARTICLE_TITLE_MIN_LENGTH) {
            errors.add(Constants.INVALID_ARTICLE_TITLE_LENGTH);
        }

        if (articleDto.getContent() == null) {
            errors.add(Constants.ARTICLE_CONTENT_NOT_NULL);
        } else if (articleDto.getContent().length() < Constants.ARTICLE_CONTENT_MIN_LENGTH) {
            errors.add(Constants.INVALID_ARTICLE_CONTENT_LENGTH);
        }

        return errors;
    }
}
