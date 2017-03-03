package softuni.services;

import softuni.dtos.ArticleDto;

import java.util.List;

public interface ArticleService {
    void save(ArticleDto articleDto);
    List<ArticleDto> getAll();
    ArticleDto getById(Long id);
    void update(ArticleDto articleDto);
    void delete(Long id);
}
