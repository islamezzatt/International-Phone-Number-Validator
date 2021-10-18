package com.example.internationalphonenumbervalidator.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.internationalphonenumbervalidator.model.CustomerDto;
import com.example.internationalphonenumbervalidator.model.CustomerResponse;
import com.example.internationalphonenumbervalidator.service.CustomerService;
import com.example.internationalphonenumbervalidator.utils.JsonUtil;

public class CustomerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void test_getCustomerPhoneNumbers_success() throws Exception {
        when(customerService.getCustomerPhoneNumbers(anyInt(), anyInt())).thenReturn(getCustomerResponseDto());
        mockMvc.perform(get("/customer/contact").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    private CustomerResponse getCustomerResponseDto() {
        CustomerResponse customerResponse = new CustomerResponse();
        List<CustomerDto> customerDto = JsonUtil.fromResourcePathUsingListObjectMapper("/sample-response.json",
                CustomerDto.class);
        customerResponse.setResultItems(customerDto.size());
        customerResponse.setTotalItems(10);
        return customerResponse;
    }
}
