package services;

import dtos.OrderDto;
import models.Message;
import models.Order;
import org.modelmapper.ModelMapper;
import repositories.OrderRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderRepository orderRepository;

    @Override
    public void addOrder(OrderDto orderDto) {
        ModelMapper modelMapper = new ModelMapper();
        Order order = modelMapper.map(orderDto, Order.class);
        this.orderRepository.addOrder(order);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orders = this.orderRepository.getAllOrders();
        List<OrderDto> orderDtos = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (Order order : orders) {
            OrderDto orderDto = modelMapper.map(order,OrderDto.class);
            orderDtos.add(orderDto);
        }
        return orderDtos;
    }

    @Override
    public OrderDto getById(Long id) {
        Order order = this.orderRepository.getById(id);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(order,OrderDto.class);
    }

    @Override
    public void update(OrderDto orderDto, Long id) {
        Order order = this.orderRepository.getById(id);
        order.setStatus(orderDto.getStatus());
        this.orderRepository.addOrder(order);
    }


}
