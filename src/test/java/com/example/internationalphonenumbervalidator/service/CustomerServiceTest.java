package com.example.internationalphonenumbervalidator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.internationalphonenumbervalidator.entities.Customer;
import com.example.internationalphonenumbervalidator.mapper.CustomerMapper;
import com.example.internationalphonenumbervalidator.model.CustomerResponse;
import com.example.internationalphonenumbervalidator.repository.CustomerRepository;
import com.example.internationalphonenumbervalidator.utils.JsonUtil;

@SpringBootTest
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerService customerService;

    @Test
    public void test_getCustomerPhoneNumbers() throws IOException {
        when(customerRepository.findAll()).thenReturn(getCustomerList());
        when(customerMapper.fromCustomerEntityList(any())).thenReturn(getCustomerResponse().getCustomers());
        CustomerResponse customerResponse = customerService.getCustomerPhoneNumbers(0, 5);
        assertEquals(12, customerResponse.getCustomers().size());
    }

    @Test
    public void test_paginateCustomerList_firstFullPage() {
        List<Customer> paginatedCustomerList = customerService.paginateCustomerList(getCustomerList(), 0, 9);
        assertEquals(9, paginatedCustomerList.size());
    }

    @Test
    public void test_paginateCustomerList_lastPage() {
        List<Customer> paginatedCustomerList = customerService.paginateCustomerList(getCustomerList(), 2, 9);
        assertEquals(2, paginatedCustomerList.size());
    }

    @Test
    public void test_paginateCustomerList_notExistingPage() {
        List<Customer> paginatedCustomerList = customerService.paginateCustomerList(getCustomerList(), 4, 9);
        assertEquals(0, paginatedCustomerList.size());
    }

    @Test
    public void test_buildCustomerResponse() throws IOException {
        CustomerResponse customerResponse = customerService.buildCustomerResponse(0, 3, 15,
                getCustomerResponse().getCustomers());
        assertEquals(12, customerResponse.getCustomers().size());
        assertEquals(15, customerResponse.getTotalItems());
        assertEquals(5, customerResponse.getTotalPages());
        assertEquals(0, customerResponse.getCurrentPage());
        assertEquals(12, customerResponse.getResultItems());
    }

    private List<Customer> getCustomerList() {
        return JsonUtil.fromResourcePathUsingListObjectMapper("/customers-response.json", Customer.class);
    }

    private CustomerResponse getCustomerResponse() throws IOException {
        return JsonUtil.fromResourcePath("/sample-response.json", CustomerResponse.class);
    }
}
