package ee.ordermanagmentsystem;

import ee.ordermanagmentsystem.dto.OrderDTO;
import ee.ordermanagmentsystem.dto.OrderLineDTO;
import ee.ordermanagmentsystem.mapper.OrderMapper;
import ee.ordermanagmentsystem.model.Order;
import ee.ordermanagmentsystem.repository.OrderRepository;
import ee.ordermanagmentsystem.service.OrderService;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateOrder() {

        OrderLineDTO line1 = new OrderLineDTO();
        line1.setProductId(1L);

        OrderLineDTO line2 = new OrderLineDTO();
        line2.setProductId(2L);

        List<OrderLineDTO> orderLineDTOs = Arrays.asList(line1, line2);

        OrderDTO mockOrderDTO = new OrderDTO();
        mockOrderDTO.setCustomerId(123L);
        mockOrderDTO.setOrderLines(orderLineDTOs);

        Order mockOrder = new Order();
        Order savedOrder = new Order();
        OrderDTO expectedOrderDTO = new OrderDTO();
        expectedOrderDTO.setCustomerId(123L);

        when(orderMapper.toEntity(mockOrderDTO)).thenReturn(mockOrder);
        when(orderRepository.save(mockOrder)).thenReturn(savedOrder);
        when(orderMapper.toDTO(savedOrder)).thenReturn(expectedOrderDTO);

        OrderDTO resultOrderDTO = orderService.createOrder(mockOrderDTO);

        assertSame(expectedOrderDTO, resultOrderDTO);
        verify(orderRepository).save(mockOrder);
        verify(orderMapper).toEntity(mockOrderDTO);
        verify(orderMapper).toDTO(savedOrder);
    }

    @Test
    public void testGetOrdersByDate() {
        LocalDate testDate = LocalDate.of(2023, 9, 25);

        Order testOrder = new Order();
        testOrder.setSubmissionDate(testDate);
        List<Order> orderList = Collections.singletonList(testOrder);

        when(orderRepository.findBySubmissionDate(testDate)).thenReturn(orderList);

        List<Order> returnedOrders = orderService.getOrdersByDate(testDate);

        assertFalse(returnedOrders.isEmpty());
        assertEquals(testOrder, returnedOrders.get(0));

        verify(orderRepository).findBySubmissionDate(testDate);
    }
}
