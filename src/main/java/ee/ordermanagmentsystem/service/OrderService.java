package ee.ordermanagmentsystem.service;

import ee.ordermanagmentsystem.dto.OrderDTO;
import ee.ordermanagmentsystem.mapper.OrderMapper;
import ee.ordermanagmentsystem.model.Order;
import ee.ordermanagmentsystem.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    private OrderMapper orderMapper;

    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = orderMapper.toEntity(orderDTO);
        order = orderRepository.save(order);

        return orderMapper.toDTO(order);
    }
}
