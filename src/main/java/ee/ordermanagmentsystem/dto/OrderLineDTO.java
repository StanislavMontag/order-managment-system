package ee.ordermanagmentsystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderLineDTO {
    private Long productId;
    private int quantity;
}
