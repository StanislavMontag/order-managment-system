package ee.ordermanagmentsystem.mapper;

import ee.ordermanagmentsystem.dto.OrderDTO;
import ee.ordermanagmentsystem.dto.OrderLineDTO;
import ee.ordermanagmentsystem.model.Order;
import ee.ordermanagmentsystem.model.OrderLine;
import ee.ordermanagmentsystem.model.Customer;
import ee.ordermanagmentsystem.model.Product;
import ee.ordermanagmentsystem.repository.CustomerRepository;
import ee.ordermanagmentsystem.repository.ProductRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring")
public abstract class OrderMapper {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;

    // Mapping between OrderDTO and Order entity
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "customer", expression = "java(mapCustomer(orderDTO.getCustomerId()))"),
            @Mapping(target = "orderLines", source = "orderLines")
    })
    public abstract Order toEntity(OrderDTO orderDTO);

    @Mapping(source = "customer.id", target = "customerId")
    public abstract OrderDTO toDTO(Order order);

    // Mapping between OrderLineDTO and OrderLine entity
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "product", expression = "java(mapProduct(orderLineDTO.getProductId()))")
    })
    @Mapping(target = "order", ignore = true)
    public abstract OrderLine orderLineToEntity(OrderLineDTO orderLineDTO);

    @Mapping(source = "product.id", target = "productId")
    public abstract OrderLineDTO orderLineToDTO(OrderLine orderLine);

    public Customer mapCustomer(Long customerId) {
        if (customerId == null) {
            return null;
        }
        return customerRepository.findById(customerId).orElse(null);
    }

    public Product mapProduct(Long productId) {
        if (productId == null) {
            return null;
        }
        return productRepository.findById(productId).orElse(null);
    }
}
