package ee.ordermanagmentsystem.service;

import ee.ordermanagmentsystem.dto.ProductDTO;
import ee.ordermanagmentsystem.mapper.ProductMapper;
import ee.ordermanagmentsystem.model.Product;
import ee.ordermanagmentsystem.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        product = productRepository.save(product);
        return productMapper.toDTO(product);
    }
}
