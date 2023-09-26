package ee.ordermanagmentsystem.controller;

import ee.ordermanagmentsystem.dto.OrderDTO;
import ee.ordermanagmentsystem.model.Order;
import ee.ordermanagmentsystem.service.OrderService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<List<Order>> getOrdersByDate(@RequestParam String date) {
        LocalDate submissionDate = LocalDate.parse(date);  // assumes date format as "yyyy-MM-dd"
        List<Order> orders = orderService.getOrdersByDate(submissionDate);
        return ResponseEntity.ok(orders);
    }
}
