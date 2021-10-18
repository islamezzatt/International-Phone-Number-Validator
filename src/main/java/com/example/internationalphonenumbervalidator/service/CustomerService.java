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
        Pageable pageable = PageRequest.of(offset, limit);
        Page<Customer> customerPagedList = customerRepository.findAll(pageable);
        List<CustomerDto> customerDtoList = customerMapper.fromCustomerEntityList(customerPagedList.toList());

        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setCustomers(customerDtoList);
        customerResponse.setCurrentPage(customerPagedList.getNumber());
        customerResponse.setTotalPages(customerPagedList.getTotalPages());
        customerResponse.setTotalItems(customerPagedList.getTotalElements());
        customerResponse.setResultItems(customerPagedList.getNumberOfElements());
        return customerResponse;
    }
}
