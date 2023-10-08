package ee.ordermanagmentsystem;

import ee.ordermanagmentsystem.dto.ProductDTO;
import ee.ordermanagmentsystem.mapper.ProductMapper;
import ee.ordermanagmentsystem.model.Product;
import ee.ordermanagmentsystem.repository.ProductRepository;
import ee.ordermanagmentsystem.service.ProductService;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testCreateProduct() {

        ProductDTO dto = ProductDTO.builder()
                .name("Test Product")
                .skuCode("TP123")
                .unitPrice(new BigDecimal("99.99"))
                .build();

        Product product = new Product();
        product.setId(1L);
        product.setName(dto.getName());
        product.setSkuCode(dto.getSkuCode());
        product.setUnitPrice(dto.getUnitPrice());

        when(productMapper.toEntity(dto)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.toDTO(product)).thenReturn(dto);

        ProductDTO result = productService.createProduct(dto);

        assertNotNull(result);
        assertEquals("Test Product", result.getName());
        assertEquals("TP123", result.getSkuCode());
        assertEquals(new BigDecimal("99.99"), result.getUnitPrice());

        verify(productMapper).toEntity(dto);
        verify(productRepository).save(product);
        verify(productMapper).toDTO(product);
    }
}
