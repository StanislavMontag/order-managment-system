package ee.ordermanagmentsystem.service;

import ee.ordermanagmentsystem.dto.OrderDTO;
import ee.ordermanagmentsystem.mapper.OrderMapper;
import ee.ordermanagmentsystem.model.Order;
import ee.ordermanagmentsystem.model.OrderLine;
import ee.ordermanagmentsystem.repository.OrderRepository;
import ee.ordermanagmentsystem.repository.specification.OrderSpecification;
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

    public List<Order> searchByProductNameWithJPQL(String productName) {
        return orderRepository.findByProductNameJPQL(productName);
    }

    public List<Order> searchByProductNameWithCriteria(String productName) {
        return orderRepository.findAll(OrderSpecification.hasProductName(productName));
    }

}
