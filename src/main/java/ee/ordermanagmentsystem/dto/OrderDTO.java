package ee.ordermanagmentsystem.dto;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class OrderDTO {
    private Long id;
    private Long customerId;
    private LocalDate submissionDate;
    private List<OrderLineDTO> orderLines;
}

