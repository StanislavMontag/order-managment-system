package ee.ordermanagmentsystem.service;

import ee.ordermanagmentsystem.dto.CustomerDTO;
import ee.ordermanagmentsystem.mapper.CustomerMapper;
import ee.ordermanagmentsystem.model.Customer;
import ee.ordermanagmentsystem.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    /**
     * Creates a new customer based on provided DTO
     *
     * @param customerDTO The data transfer object representing the customer details.
     * @return the created CustomerDTO
     */
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);
        customer = customerRepository.save(customer);
        return customerMapper.toDTO(customer);
    }
}

