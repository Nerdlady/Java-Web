package productsDelivery.repository;

import productsDelivery.models.Status;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class StatusRepository {
    public Status getStatusByName(String name){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Status status = (Status) entityManager.createQuery("SELECT s FROM Status  AS s WHERE s.name = '" + name + "'").getSingleResult();

        entityManager.close();
        entityManagerFactory.close();
        return status;
    }
}
