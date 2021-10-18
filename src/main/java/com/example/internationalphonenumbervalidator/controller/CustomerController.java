package com.example.internationalphonenumbervalidator.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.internationalphonenumbervalidator.model.CustomerResponse;
import com.example.internationalphonenumbervalidator.model.CustomerDto;
import com.example.internationalphonenumbervalidator.service.CustomerService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/contact", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerDto>> getCustomerPhoneNumbers(
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "limit", defaultValue = "20") int limit) {
        CustomerResponse customerResponse = customerService.getCustomerPhoneNumbers(offset, limit);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("X-Result-Count", String.valueOf(customerResponse.getResultCount()));
        responseHeaders.set("X-Total-Count", String.valueOf(customerResponse.getTotalCount()));
        return ResponseEntity.ok().headers(responseHeaders).body(customerResponse.getCustomerResponseDtoList());
    }
}
