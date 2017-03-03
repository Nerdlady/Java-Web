package softuni.repositories;

import softuni.models.Article;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Local(ArticleRepository.class)
public class ArticleRepositoryImpl implements ArticleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Article article) {
        this.entityManager.persist(article);
    }

    @Override
    public List<Article> getAll() {
        Query query = this.entityManager.createQuery("SELECT a FROM articles AS a");
        List<Article> articles = new ArrayList<>();
        if (query.getResultList().size() > 0){
            articles = query.getResultList();
        }
        return articles;
    }

    @Override
    public Article getById(Long id) {
        Query query = this.entityManager.createQuery("SELECT a FROM articles AS a WHERE a.id = :id");
        query.setParameter("id",id);
        Article article = null;
        if (query.getResultList().size() > 0){
            article = (Article) query.getSingleResult();
        }
        return article;
    }

    @Override
    public void delete(Article article) {
        this.entityManager.remove(article);
    }
}
