package ee.ordermanagmentsystem.repository;

import ee.ordermanagmentsystem.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
}
