package ee.ordermanagmentsystem.mapper;

import ee.ordermanagmentsystem.dto.ProductDTO;
import ee.ordermanagmentsystem.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    Product toEntity(ProductDTO productDTO);

    ProductDTO toDTO(Product product);
}