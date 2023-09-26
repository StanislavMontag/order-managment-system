package ee.ordermanagmentsystem.repository;

import ee.ordermanagmentsystem.model.Order;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
    @Query("SELECT o FROM Order o JOIN o.orderLines ol WHERE ol.product.name = :productName")
    List<Order> findByProductNameJPQL(String productName);

    List<Order> findBySubmissionDate(LocalDate submissionDate);
}
