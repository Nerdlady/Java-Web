package softuni.repositories;

import softuni.models.User;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@Local(UserRepository.class)
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(User user) {
        this.entityManager.persist(user);
    }

    @Override
    public User getUserByEmail(String email) {
        Query query = this.entityManager.createQuery("SELECT u FROM users AS u WHERE u.email = :email");
        query.setParameter("email",email);
        User user = null;
        if (query.getResultList().size() > 0){
            user = (User) query.getSingleResult();
        }
        return user;
    }
}
