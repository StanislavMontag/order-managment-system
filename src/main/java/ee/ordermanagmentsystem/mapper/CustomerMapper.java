package ee.ordermanagmentsystem.mapper;

import ee.ordermanagmentsystem.dto.CustomerDTO;
import ee.ordermanagmentsystem.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDTO toDTO(Customer customer);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orders", ignore = true)
    Customer toEntity(CustomerDTO customerDTO);

}
