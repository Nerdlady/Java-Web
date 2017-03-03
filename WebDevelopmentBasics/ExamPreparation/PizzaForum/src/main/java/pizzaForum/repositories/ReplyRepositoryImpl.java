package pizzaForum.repositories;

import pizzaForum.entities.Reply;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Local(ReplyRepository.class)
public class ReplyRepositoryImpl implements ReplyRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Reply reply) {
        this.entityManager.persist(reply);
    }

    @Override
    public List<Reply> getAll(Long id) {
        Query query = this.entityManager.createQuery("SELECT r FROM Reply AS r WHERE r.topic.id = :id");
        query.setParameter("id",id);
        List<Reply> replies = new ArrayList<>();
        if (query.getResultList().size() > 0) {
            replies = query.getResultList();
        }
        return replies;
    }
}
