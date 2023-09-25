package ee.ordermanagmentsystem.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ProductDTO {
    private String name;
    private String skuCode;
    private BigDecimal unitPrice;
}

