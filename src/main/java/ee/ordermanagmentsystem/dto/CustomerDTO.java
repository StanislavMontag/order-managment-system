package ee.ordermanagmentsystem.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerDTO {
    private Long id;
    private String registrationCode;
    private String fullName;
    private String email;
    private String telephone;
}

