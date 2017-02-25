package repositories;

import models.Order;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@Local(OrderRepository.class)
public class OrderRepositoryImpl implements OrderRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addOrder(Order order) {
        this.entityManager.persist(order);
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = this.entityManager.createQuery("SELECT o FROM orders AS o").getResultList();
        return orders;
    }

    @Override
    public Order getById(Long id) {
        Query query = this.entityManager.createQuery("SELECT o FROM orders AS o WHERE o.id = :id");
        query.setParameter("id",id);
        return (Order) query.getSingleResult();
    }
}
