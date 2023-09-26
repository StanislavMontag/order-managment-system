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
     * Creates new customer based on DTO object accepted and saves it to db
     * @param customerDTO The UserDto object to be saved as a User entity.
     * @return the saved Customer entity as a CustomerDTO object
     */
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);
        customer = customerRepository.save(customer);
        return customerMapper.toDTO(customer);
    }
}

