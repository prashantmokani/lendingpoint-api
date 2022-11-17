package com.prashant.lendingpoint.api.customer;

import com.prashant.lendingpoint.api.customer.entities.Customer;
import com.prashant.lendingpoint.api.customer.models.CustomerModel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public void createCustomer(final CustomerModel customerModel) {
        log.info("Creating customer details = {}", customerModel);
        final Customer customerToCreate = customerMapper.modelToCustomer(customerModel);
        customerRepository.save(customerToCreate);
    }

}
