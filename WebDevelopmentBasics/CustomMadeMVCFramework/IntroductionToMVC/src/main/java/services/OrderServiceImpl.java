package services;

import dtos.OrderDto;
import models.Order;
import org.modelmapper.ModelMapper;
import repositories.OrderRepository;
import repositories.OrderRepositoryImpl;

public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;

    public OrderServiceImpl() {
         this.orderRepository = OrderRepositoryImpl.getInstance();
    }

    @Override
    public void addOrder(OrderDto orderDto) {
        ModelMapper modelMapper = new ModelMapper();
        Order order = modelMapper.map(orderDto, Order.class);
        this.orderRepository.addOrder(order);
    }
}
