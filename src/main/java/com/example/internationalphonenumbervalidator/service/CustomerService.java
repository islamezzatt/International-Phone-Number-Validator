package com.example.internationalphonenumbervalidator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.internationalphonenumbervalidator.entities.Customer;
import com.example.internationalphonenumbervalidator.mapper.CustomerMapper;
import com.example.internationalphonenumbervalidator.model.CustomerDto;
import com.example.internationalphonenumbervalidator.model.CustomerResponse;
import com.example.internationalphonenumbervalidator.repository.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public CustomerResponse getCustomerPhoneNumbers(int offset, int limit) {
        CustomerResponse customerResponse = new CustomerResponse();
        Pageable pageable = PageRequest.of(offset, limit);
        Page<Customer> customerPagedList = customerRepository.findAll(pageable);
        List<CustomerDto> customerDtoList = customerMapper.fromCustomerEntityList(customerPagedList.toList());
        customerResponse.setTotalCount(customerPagedList.getTotalElements());
        customerResponse.setResultCount(customerPagedList.getNumberOfElements());
        customerResponse.setCustomerResponseDtoList(customerDtoList);
        return customerResponse;
    }
}
