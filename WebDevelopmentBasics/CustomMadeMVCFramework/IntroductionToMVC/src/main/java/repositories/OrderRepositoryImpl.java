package repositories;

import models.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {
    private List<Order> orders;
    private static OrderRepository orderRepository;

    public static OrderRepository getInstance(){
        if (orderRepository == null){
            orderRepository = new OrderRepositoryImpl();
        }
        return orderRepository;
    }

    private OrderRepositoryImpl() {
        this.orders = new ArrayList<>();
    }

    @Override
    public void addOrder(Order order) {
        this.orders.add(order);
    }
}
