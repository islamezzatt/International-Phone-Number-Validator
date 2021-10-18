package com.example.internationalphonenumbervalidator.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.example.internationalphonenumbervalidator.model.CustomerResponse;
import com.example.internationalphonenumbervalidator.service.CustomerService;
import com.example.internationalphonenumbervalidator.utils.JsonUtil;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    public void test_getCustomerPhoneNumbers_success() throws Exception {
        when(customerService.getCustomerPhoneNumbers(anyInt(), anyInt())).thenReturn(getCustomerResponseDto());
        mockMvc.perform(get("/customer/contact").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.resultItems", Matchers.is(12)))
                .andExpect(jsonPath("$.totalItems", Matchers.is(15)))
                .andExpect(jsonPath("$.currentPage", Matchers.is(0)))
                .andExpect(jsonPath("$.totalPages", Matchers.is(2)));
    }

    private CustomerResponse getCustomerResponseDto() throws IOException {
        CustomerResponse customerResponse = JsonUtil.fromResourcePath("/sample-response.json", CustomerResponse.class);
        return customerResponse;
    }
}
