package pizzaForum.services;

import pizzaForum.models.bindingModels.category.AddCategory;
import pizzaForum.models.bindingModels.category.CategoryModel;
import pizzaForum.models.viewModels.ViewCategory;

import java.util.List;

public interface CategoryService {
    void save(AddCategory category);
    List<ViewCategory> getAllCategories();
    ViewCategory getById(Long id);
    void update(CategoryModel editCategory);
    void delete(Long id);
    CategoryModel findByName(String name);
}
