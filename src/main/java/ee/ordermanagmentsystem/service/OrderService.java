package ee.ordermanagmentsystem.service;

import ee.ordermanagmentsystem.dto.OrderDTO;
import ee.ordermanagmentsystem.mapper.OrderMapper;
import ee.ordermanagmentsystem.model.Order;
import ee.ordermanagmentsystem.model.OrderLine;
import ee.ordermanagmentsystem.repository.OrderRepository;
import ee.ordermanagmentsystem.repository.specification.OrderSpecification;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = orderMapper.toEntity(orderDTO);

        for(OrderLine orderLine : order.getOrderLines()) {
            orderLine.setOrder(order);
        }

        order = orderRepository.save(order);
        return orderMapper.toDTO(order);
    }

    public List<OrderDTO> getOrdersByDate(LocalDate submissionDate) {
        List<Order> orders = orderRepository.findBySubmissionDate(submissionDate);
        return orders.stream().map(orderMapper::toDTO).collect(Collectors.toList());
    }

    public List<OrderDTO> searchByProductNameWithJPQL(String productName) {
        List<Order> orders = orderRepository.findByProductNameJPQL(productName);
        return orders.stream().map(orderMapper::toDTO).collect(Collectors.toList());
    }

    public List<Order> searchByProductNameWithCriteria(String productName) {
        return orderRepository.findAll(OrderSpecification.hasProductName(productName));
    }

    public OrderDTO updateOrderLineQuantity(Long orderId, Long orderLineId, int newQuantity) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        OrderLine targetOrderLine = order.getOrderLines().stream()
                .filter(orderLine -> orderLine.getId().equals(orderLineId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Order line not found"));

        targetOrderLine.setQuantity(newQuantity);

        Order updatedOrder = orderRepository.save(order);

        return orderMapper.toDTO(updatedOrder);
    }
}
