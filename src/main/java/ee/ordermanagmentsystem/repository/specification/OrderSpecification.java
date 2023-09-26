package ee.ordermanagmentsystem.repository.specification;

import ee.ordermanagmentsystem.model.Order;
import ee.ordermanagmentsystem.model.OrderLine;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class OrderSpecification {
    public static Specification<Order> hasProductName(String productName) {
        return (root, query, cb) -> {
            Join<Order, OrderLine> joinOrderLines = root.join("orderLines");
            Predicate hasProductName = cb.equal(joinOrderLines.get("product").get("name"), productName);
            return hasProductName;
        };
    }
}
