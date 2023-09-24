package ee.ordermanagmentsystem.service;

import ee.ordermanagmentsystem.model.Order;
import ee.ordermanagmentsystem.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }
}
