package repositories;

import models.Order;

import java.util.List;

public interface OrderRepository {
    void addOrder(Order order);
    List<Order> getAllOrders();
    Order getById(Long id);
}
