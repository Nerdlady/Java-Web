package pizzaForum.repositories;

import pizzaForum.entities.Topic;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Local(TopicRepository.class)
public class TopicRepositoryImpl implements TopicRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Topic topic) {
        this.entityManager.persist(topic);
    }

    @Override
    public List<Topic> getLastTen() {
        Query query = this.entityManager.createQuery("SELECT t FROM Topic AS t ORDER BY t.publishDate DESC ").setMaxResults(10);
        List<Topic> topics = new ArrayList<>();
        if (query.getResultList().size() > 0) {
            topics = query.getResultList();
        }
        return topics;
    }

    @Override
    public Topic getById(Long id) {
        Query query = this.entityManager.createQuery("SELECT t FROM Topic AS t WHERE t.id = :id");
        query.setParameter("id", id);

        return (Topic) query.getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public List<Topic> getTopicsByCategoryId(Long id) {
        Query query = this.entityManager.createQuery("SELECT t FROM Topic AS t WHERE t.category.id = :id  ORDER BY t.publishDate DESC ").setMaxResults(10);
        query.setParameter("id",id);
        List<Topic> topics = new ArrayList<>();
        if (query.getResultList().size() > 0) {
            topics = query.getResultList();
        }
        return topics;
    }

    @Override
    public void delete(Long id) {
        Query query = this.entityManager.createQuery("DELETE FROM Topic AS t WHERE t.id = :id");
        query.setParameter("id",id);
        query.executeUpdate();
    }


}
