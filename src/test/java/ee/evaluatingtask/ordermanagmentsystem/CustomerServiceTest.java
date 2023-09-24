package ee.evaluatingtask.ordermanagmentsystem;

import ee.ordermanagmentsystem.dto.CustomerDTO;
import ee.ordermanagmentsystem.mapper.CustomerMapper;
import ee.ordermanagmentsystem.model.Customer;
import ee.ordermanagmentsystem.repository.CustomerRepository;
import ee.ordermanagmentsystem.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerService customerService;

    @Test
    public void testCreateCustomer() {

        CustomerDTO dto = new CustomerDTO();
        dto.setRegistrationCode("RC1234");
        dto.setFullName("John Doe");
        dto.setEmail("john.doe@example.com");
        dto.setTelephone("123-456-7890");

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setRegistrationCode(dto.getRegistrationCode());
        customer.setFullName(dto.getFullName());
        customer.setEmail(dto.getEmail());
        customer.setTelephone(dto.getTelephone());

        when(customerMapper.toEntity(dto)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerMapper.toDTO(customer)).thenReturn(dto);

        CustomerDTO result = customerService.createCustomer(dto);

        assertNotNull(result);
        assertEquals("RC1234", result.getRegistrationCode());
        assertEquals("John Doe", result.getFullName());
        assertEquals("john.doe@example.com", result.getEmail());
        assertEquals("123-456-7890", result.getTelephone());

        verify(customerMapper, times(1)).toEntity(dto);
        verify(customerRepository, times(1)).save(customer);
        verify(customerMapper, times(1)).toDTO(customer);
    }
}
