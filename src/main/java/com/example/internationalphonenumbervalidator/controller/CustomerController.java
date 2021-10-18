package com.example.internationalphonenumbervalidator.controller;

import com.example.internationalphonenumbervalidator.model.CustomerResponse;
import com.example.internationalphonenumbervalidator.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/contact", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerResponse> getCustomerPhoneNumbers(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) {
        CustomerResponse customerResponse = customerService.getCustomerPhoneNumbers(page, pageSize);
        return ResponseEntity.ok(customerResponse);
    }
}
