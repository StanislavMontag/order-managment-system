package ee.ordermanagmentsystem.mapper;

import ee.ordermanagmentsystem.dto.ProductDTO;
import ee.ordermanagmentsystem.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductDTO productDTO);

    ProductDTO toDTO(Product product);
}