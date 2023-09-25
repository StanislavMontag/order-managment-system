package ee.ordermanagmentsystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDTO {
    private Long customerId;
    private List<OrderLineDTO> orderLines;
}

