package repositories;

import models.Message;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@Local(MessageRepository.class)
public class MessageRepositoryImpl implements MessageRepository {

    @PersistenceContext
    private EntityManager entityManager;



    @Override
    public void save(Message message) {
        this.entityManager.persist(message);
    }

    @Override
    public List<Message> getAllMessages() {
        List<Message> messages = this.entityManager.createQuery("SELECT m FROM messages AS m").getResultList();
        return messages;
    }

    @Override
    public Message getById(Long id) {
        Query query = this.entityManager.createQuery("SELECT m FROM messages AS m WHERE m.id = :id");
        query.setParameter("id",id);
        return (Message) query.getSingleResult();
    }

    @Override
    public void delete(Message message) {
        this.entityManager.remove(message);
    }
}
