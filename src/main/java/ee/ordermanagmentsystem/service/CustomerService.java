package ee.ordermanagmentsystem.service;

import ee.ordermanagmentsystem.model.Customer;
import ee.ordermanagmentsystem.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}

