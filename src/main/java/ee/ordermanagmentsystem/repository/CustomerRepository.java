package ee.ordermanagmentsystem.repository;

import ee.ordermanagmentsystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}