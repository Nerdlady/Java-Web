package pizzaMore.repositories;

import pizzaMore.models.entities.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class SessionRepository {
     public long createSession(Session session){
         Long id;
         EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
         EntityManager entityManager = entityManagerFactory.createEntityManager();
         entityManager.getTransaction().begin();
         entityManager.persist(session);
         id = session.getId();
         entityManager.getTransaction().commit();
         entityManager.close();
         entityManagerFactory.close();
         return id;
     }

    public Session findById(Long id){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Session session = getSession(id, entityManager);

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
        return session;
    }

    public void delete(Long id){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        Session session = getSession(id, entityManager);

        entityManager.remove(session);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    private Session getSession(Long id, EntityManager entityManager) {
        Query query = entityManager.createQuery("SELECT s FROM Session AS s WHERE s.id = :id");
        query.setParameter("id",id);
        Session session = null;
        if (query.getResultList().size() != 0){
            session = (Session) query.getSingleResult();
        }
        return session;
    }
}
