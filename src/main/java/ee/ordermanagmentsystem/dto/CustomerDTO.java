package ee.ordermanagmentsystem.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class CustomerDTO {
    private String registrationCode;
    private String fullName;
    private String email;
    private String telephone;
}
