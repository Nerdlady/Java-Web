package pizzaMore.repositories;

import pizzaMore.models.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class UserRepository {
    public void createUser(User user) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    public User findUserByUsernameAndPassword(String username, String password) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("SELECT u FROM User AS u WHERE u.name = :username AND u.password = :password");
        query.setParameter("username", username);
        query.setParameter("password", password);

        User user = null;
        if (query.getResultList().size() != 0){
            user = (User) query.getSingleResult();
        }

        entityManager.close();
        entityManagerFactory.close();
        return user;
    }
}
