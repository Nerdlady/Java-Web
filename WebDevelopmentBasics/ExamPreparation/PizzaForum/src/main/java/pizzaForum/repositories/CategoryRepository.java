package pizzaForum.repositories;

import pizzaForum.entities.Category;

import java.util.List;

public interface CategoryRepository {
    void save(Category category);
    List<Category> getAllCategories();
    Category getById(Long id);
    void update(Category category);
    void delete(Long id);
    Category findByName(String name);
}
