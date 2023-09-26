package ee.ordermanagmentsystem.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CustomerDTO {
    private String registrationCode;
    private String fullName;
    private String email;
    private String telephone;
}

