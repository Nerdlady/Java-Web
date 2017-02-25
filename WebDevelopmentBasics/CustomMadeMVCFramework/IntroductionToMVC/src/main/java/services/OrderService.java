package services;

import dtos.OrderDto;

import java.util.List;

public interface OrderService {
    void addOrder(OrderDto orderDto);
    List<OrderDto> getAllOrders();
    OrderDto getById(Long id);
    void update(OrderDto orderDto,Long id);

}
