package ee.ordermanagmentsystem.controller;

import ee.ordermanagmentsystem.dto.OrderDTO;
import ee.ordermanagmentsystem.service.OrderService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        OrderDTO savedOrder = orderService.createOrder(orderDTO);
        return ResponseEntity.ok(savedOrder);
    }

    @GetMapping("/by-date")
    public ResponseEntity<List<OrderDTO>> getOrdersByDate(@RequestParam String date) {
        LocalDate submissionDate = LocalDate.parse(date);  // assumes date format as "yyyy-MM-dd"
        List<OrderDTO> orderDTOs = orderService.getOrdersByDate(submissionDate);
        return ResponseEntity.ok(orderDTOs);
    }

    @GetMapping("/by-product/jpql")
    public ResponseEntity<List<OrderDTO>> searchOrdersByProductName(@RequestParam String product) {
        List<OrderDTO> orders = orderService.searchByProductNameWithJPQL(product);
        return ResponseEntity.ok(orders);
    }
    @GetMapping("/by-product/criteria")
    public ResponseEntity<List<OrderDTO>> searchorderByProductName(@RequestParam String product) {
        List<OrderDTO> orders = orderService.searchByProductNameWithCriteria(product);
        return ResponseEntity.ok(orders);
    }

    @PatchMapping("/{orderId}/order-lines/{orderLineId}/quantity")
    public ResponseEntity<?> updateOrderLineQuantity(
            @PathVariable Long orderId,
            @PathVariable Long orderLineId,
            @RequestParam Integer newQuantity) {
        OrderDTO updatedOrder = orderService.updateOrderLineQuantity(orderId, orderLineId, newQuantity);
        return ResponseEntity.ok(updatedOrder);
    }
}
