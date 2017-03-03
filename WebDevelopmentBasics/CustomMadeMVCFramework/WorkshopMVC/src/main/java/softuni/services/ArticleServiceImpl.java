package softuni.services;

import org.modelmapper.ModelMapper;
import softuni.dtos.ArticleDto;
import softuni.models.Article;
import softuni.repositories.ArticleRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ArticleServiceImpl implements ArticleService {

    @Inject
    private ArticleRepository articleRepository;

    @Override
    public void save(ArticleDto articleDto) {
        ModelMapper modelMapper = new ModelMapper();
        Article article = modelMapper.map(articleDto,Article.class);
        this.articleRepository.save(article);
    }

    @Override
    public List<ArticleDto> getAll() {
        List<Article> articles = this.articleRepository.getAll();
        List<ArticleDto> articleDtos = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (Article article : articles) {
            ArticleDto articleDto = modelMapper.map(article,ArticleDto.class);
            articleDtos.add(articleDto);
        }
        return articleDtos;
    }

    @Override
    public ArticleDto getById(Long id) {
        Article article = this.articleRepository.getById(id);
        ArticleDto articleDto = null;
        ModelMapper modelMapper = new ModelMapper();
        if (article != null){
            articleDto = modelMapper.map(article,ArticleDto.class);
        }
        return articleDto;
    }

    @Override
    public void update(ArticleDto articleDto) {
        Article article = this.articleRepository.getById(articleDto.getId());
        article.setTitle(articleDto.getTitle());
        article.setContent(articleDto.getContent());
        this.articleRepository.save(article);
    }

    @Override
    public void delete(Long id) {
        Article article = this.articleRepository.getById(id);
        this.articleRepository.delete(article);
    }
}
