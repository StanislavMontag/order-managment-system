package ee.ordermanagmentsystem.mapper;

import ee.ordermanagmentsystem.dto.OrderDTO;
import ee.ordermanagmentsystem.dto.OrderLineDTO;
import ee.ordermanagmentsystem.model.Order;
import ee.ordermanagmentsystem.model.OrderLine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "customer.id", target = "customerId")
    OrderDTO toDTO(Order order);

    @Mapping(source = "customerId", target = "customer.id")
    Order toEntity(OrderDTO orderDTO);

    @Mapping(source = "product.id", target = "productId")
    OrderLineDTO orderLineToDTO(OrderLine orderLine);

    @Mapping(source = "productId", target = "product.id")
    @Mapping(target = "order", ignore = true)
    OrderLine orderLineToEntity(OrderLineDTO orderLineDTO);
}
