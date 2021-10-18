package com.example.internationalphonenumbervalidator.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    public CustomerResponse getCustomerPhoneNumbers(int page, int pageSize) {
        List<Customer> customerList = customerRepository.findAll();
        List<Customer> customerSubList = paginateCustomerList(customerList, page, pageSize);

        List<CustomerDto> customerDtoList = customerMapper.fromCustomerEntityList(customerSubList);

        int originalTotalSize = customerList.size();
        CustomerResponse customerResponse = buildCustomerResponse(page, pageSize, originalTotalSize, customerDtoList);
        return customerResponse;
    }

    private List<Customer> paginateCustomerList(List<Customer> customerList, int page, int pageSize) {
        List<Customer> customerSubList;
        int offset = page * pageSize;
        int lastIndex = offset + pageSize;
        if (lastIndex > customerList.size()) {
            lastIndex = customerList.size();
        }
        if (lastIndex < offset) {
            customerSubList = new ArrayList<>();
        } else {
            customerSubList = customerList.subList(offset, lastIndex);
        }
        return customerSubList;
    }

    private CustomerResponse buildCustomerResponse(int page, int pageSize, int originalTotalSize,
            List<CustomerDto> customerDtoList) {
        CustomerResponse customerResponse = CustomerResponse.builder()
                .customers(customerDtoList)
                .currentPage(page)
                .totalPages((long) Math.ceil((double) originalTotalSize / pageSize))
                .totalItems(originalTotalSize)
                .resultItems(customerDtoList.size())
                .build();

        return customerResponse;
    }
}
