package com.prashant.lendingpoint.api.customer;

import com.prashant.lendingpoint.api.customer.models.CustomerModel;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Object> customerRegistration(@RequestBody @Valid final CustomerModel customerModel) {
        customerService.createCustomer(customerModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
