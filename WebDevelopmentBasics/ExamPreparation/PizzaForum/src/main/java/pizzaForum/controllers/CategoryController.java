package pizzaForum.controllers;

import mvcFramework.annotations.controller.Controller;
import mvcFramework.annotations.parameters.ModelAttribute;
import mvcFramework.annotations.parameters.PathVariable;
import mvcFramework.annotations.request.GetMapping;
import mvcFramework.annotations.request.PostMapping;
import mvcFramework.models.Model;
import pizzaForum.constants.Constants;
import pizzaForum.models.bindingModels.category.AddCategory;
import pizzaForum.models.bindingModels.category.CategoryModel;
import pizzaForum.models.viewModels.ViewCategory;
import pizzaForum.models.viewModels.ViewTopic;
import pizzaForum.services.CategoryService;
import pizzaForum.services.TopicService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
@Controller
public class CategoryController {
    @Inject
    private CategoryService categoryService;

    @Inject
    private TopicService topicService;

    @GetMapping("/categories")
    public String getCategoriesPage(Model model) {
        List<ViewCategory> viewCategories = this.categoryService.getAllCategories();
        model.addAttribute("categories", viewCategories);
        model.addAttribute("view", "/categories/categories.jsp");
        return Constants.BASE_LAYOUT;
    }

    @GetMapping("/admin/categories/new")
    public String getAddCategoryPage(Model model) {
        model.addAttribute("view", "/admin/category-new.jsp");

        return Constants.BASE_LAYOUT;
    }

    @PostMapping("/admin/categories/new")
    public String addCategoryPage(@ModelAttribute() AddCategory category) {
        this.categoryService.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/admin/categories/edit/{id}")
    public String getEditCategoryPage(Model model, @PathVariable("id") Long id) {
        ViewCategory viewCategory = this.categoryService.getById(id);
        model.addAttribute("category", viewCategory);
        model.addAttribute("view", "/admin/category-edit.jsp");
        return Constants.BASE_LAYOUT;
    }

    @PostMapping("/admin/categories/edit/{id}")
    public String editCategoryPage(@ModelAttribute() CategoryModel category, @PathVariable("id") Long id) {
        category.setId(id);
        this.categoryService.update(category);
        return "redirect:/categories";
    }

    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        this.categoryService.delete(id);
        return "redirect:/categories";
    }

    @GetMapping("/categories/topics/{id}")
    public String getCategoryTopicsPage(Model model,@PathVariable("id") Long id){
        List<ViewTopic> viewTopics = this.topicService.getTopicsByCategoryId(id);
        model.addAttribute("topics",viewTopics);
        model.addAttribute("view","/topics/topic-template.jsp");
        return Constants.BASE_LAYOUT;
    }


}
