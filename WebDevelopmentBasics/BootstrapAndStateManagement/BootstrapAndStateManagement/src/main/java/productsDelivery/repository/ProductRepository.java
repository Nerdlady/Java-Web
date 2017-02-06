package productsDelivery.repository;

import productsDelivery.models.Product;
import productsDelivery.models.Status;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class ProductRepository {
    public List<Product> getProducts() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<Product> products = entityManager.createQuery("SELECT p FROM Product AS p").getResultList();

        entityManager.close();
        entityManagerFactory.close();
        return products;
    }

    public Product getProduct(Long id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Product product = (Product) entityManager.createQuery("SELECT p FROM Product  AS p WHERE p.id = " + id).getSingleResult();
        entityManager.close();
        entityManagerFactory.close();

        return product;
    }

    public void persist(Long id, Status status) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("UPDATE Product AS p SET p.status.id = :statusId WHERE p.id = :id");
        query.setParameter("statusId", status.getId());
        query.setParameter("id", id);
        query.executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
