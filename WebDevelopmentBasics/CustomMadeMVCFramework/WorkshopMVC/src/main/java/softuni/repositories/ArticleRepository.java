package softuni.repositories;

import softuni.models.Article;

import java.util.List;

public interface ArticleRepository {
    void save(Article article);
    List<Article> getAll();
    Article getById(Long id);
    void delete(Article article);
}
