package com.example.internationalphonenumbervalidator.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public CustomerResponse getCustomerPhoneNumbers(int page, int pageSize, String country, String state) {
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerDto> customerDtoList = customerMapper.fromCustomerEntityList(customerList);

        customerDtoList = filterResponseIfFilteringExists(country, state, customerDtoList);
        List<CustomerDto> customerSubList = paginateCustomerList(customerDtoList, page, pageSize);

        int originalTotalSize = customerDtoList.size();
        CustomerResponse customerResponse = buildCustomerResponse(page, pageSize, originalTotalSize, customerSubList);
        return customerResponse;
    }

    List<CustomerDto> filterResponseIfFilteringExists(String country, String state, List<CustomerDto> customerDtoList) {
        if (country != null && !country.isBlank()) {
            customerDtoList = customerDtoList.stream().filter(customerDto -> customerDto.getCountry().equals(country))
                    .collect(Collectors.toList());
        }
        if (state != null && !state.isBlank()) {
            customerDtoList = customerDtoList.stream().filter(customerDto -> customerDto.getState().equals(state))
                    .collect(Collectors.toList());
        }
        return customerDtoList;
    }

    List<CustomerDto> paginateCustomerList(List<CustomerDto> customerList, int page, int pageSize) {
        List<CustomerDto> customerSubList;
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

    CustomerResponse buildCustomerResponse(int page, int pageSize, int originalTotalSize,
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
