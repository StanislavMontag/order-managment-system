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

    /**
     * Creates a new order based on the provided OrderDTO.
     *
     * @param orderDTO The data transfer object representing the order details.
     * @return The created OrderDTO.
     */
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = orderMapper.toEntity(orderDTO);

        for (OrderLine orderLine : order.getOrderLines()) {
            orderLine.setOrder(order);
        }

        order = orderRepository.save(order);
        return orderMapper.toDTO(order);
    }

    /**
     * Retrieves a list of orders based on the provided submission date.
     *
     * @param submissionDate The date for which orders are to be retrieved.
     * @return A list of OrderDTOs matching the provided date.
     */
    public List<OrderDTO> getOrdersByDate(LocalDate submissionDate) {
        List<Order> orders = orderRepository.findBySubmissionDate(submissionDate);
        return orders.stream().map(orderMapper::toDTO).collect(Collectors.toList());
    }

    /**
     * Searches for orders based on the provided product name using JPQL.
     *
     * @param productName The name of the product to search for.
     * @return A list of OrderDTOs containing the specified product.
     */
    public List<OrderDTO> searchByProductNameWithJPQL(String productName) {
        List<Order> orders = orderRepository.findByProductNameJPQL(productName);
        return orders.stream().map(orderMapper::toDTO).collect(Collectors.toList());
    }

    /**
     * Searches for orders based on the provided product name using Criteria API.
     *
     * @param productName The name of the product to search for.
     * @return A list of Orders containing the specified product.
     */
    public List<OrderDTO> searchByProductNameWithCriteria(String productName) {
        List<Order> orders = orderRepository.findAll(OrderSpecification.hasProductName(productName));
        return orders.stream().map(orderMapper::toDTO).collect(Collectors.toList());
    }

    /**
     * Updates the quantity of a specific order line within an order.
     *
     * @param orderId The ID of the order to be updated.
     * @param orderLineId The ID of the order line within the order to be updated.
     * @param newQuantity The new quantity to be set for the order line.
     * @return The updated OrderDTO.
     */
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
