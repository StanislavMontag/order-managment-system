package ee.ordermanagmentsystem.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDTO {
    private Long customerId;
    private LocalDate submissionDate;
    private List<OrderLineDTO> orderLines;
}

