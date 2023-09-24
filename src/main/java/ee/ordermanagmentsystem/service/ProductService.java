package ee.ordermanagmentsystem.service;

import ee.ordermanagmentsystem.model.Product;
import ee.ordermanagmentsystem.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
}
