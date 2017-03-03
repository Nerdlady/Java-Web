package pizzaForum.services;

import pizzaForum.entities.Category;
import pizzaForum.models.bindingModels.category.AddCategory;
import pizzaForum.models.bindingModels.category.CategoryModel;
import pizzaForum.models.viewModels.ViewCategory;
import pizzaForum.repositories.CategoryRepository;
import pizzaForum.utils.ModelParser;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class CategoryServiceImpl implements CategoryService {
    @Inject
    private CategoryRepository categoryRepository;

    @Override
    public void save(AddCategory addCategory) {
        Category category = ModelParser.getInstance().map(addCategory, Category.class);
        this.categoryRepository.save(category);
    }

    @Override
    public List<ViewCategory> getAllCategories() {
        List<Category> categories = this.categoryRepository.getAllCategories();
        List<ViewCategory> viewCategories = new ArrayList<>();
        for (Category category : categories) {
            ViewCategory viewCategory = ModelParser.getInstance().map(category, ViewCategory.class);
            viewCategories.add(viewCategory);
        }
        return viewCategories;
    }

    @Override
    public ViewCategory getById(Long id) {
        Category category = this.categoryRepository.getById(id);
        ViewCategory viewCategory = null;
        if (category != null) {
            viewCategory = ModelParser.getInstance().map(category, ViewCategory.class);
        }

        return viewCategory;
    }

    @Override
    public void update(CategoryModel editCategory) {
        Category category = ModelParser.getInstance().map(editCategory, Category.class);
        this.categoryRepository.update(category);
    }

    @Override
    public void delete(Long id) {

        this.categoryRepository.delete(id);
    }

    @Override
    public CategoryModel findByName(String name) {
        Category category = this.categoryRepository.findByName(name);
        CategoryModel categoryModel = null;
        if (category != null){
            categoryModel = ModelParser.getInstance().map(category,CategoryModel.class);
        }
        return categoryModel;
    }
}
