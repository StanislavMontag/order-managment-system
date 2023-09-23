package ee.ordermanagmentsystem.repository;

import ee.ordermanagmentsystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
