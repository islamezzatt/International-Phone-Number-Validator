package com.example.internationalphonenumbervalidator.service;

import com.example.internationalphonenumbervalidator.entities.Customer;
import com.example.internationalphonenumbervalidator.mapper.CustomerMapper;
import com.example.internationalphonenumbervalidator.model.CustomerDto;
import com.example.internationalphonenumbervalidator.model.CustomerResponse;
import com.example.internationalphonenumbervalidator.repository.CustomerRepository;
import com.example.internationalphonenumbervalidator.utils.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerService customerService;

    @Test
    public void test_getCustomerPhoneNumbers() {
        when(customerRepository.findAll(any(Pageable.class))).thenReturn(getCustomerList());
        when(customerMapper.fromCustomerEntityList(any())).thenReturn(getCustomerResponseDto());
        CustomerResponse customerResponse = customerService.getCustomerPhoneNumbers(0, 5);
        assertEquals(3, customerResponse.getCustomers().size());
    }

    private Page<Customer> getCustomerList() {
        List<Customer> customerList = new ArrayList<>();
        Customer customer1 = new Customer();
        customer1.setId(0L);
        customer1.setName("Walid Hammadi");
        customer1.setPhone("(212) 6007989253");
        Customer customer2 = new Customer();
        customer2.setId(1L);
        customer2.setName("Yosaf Karrouch");
        customer2.setPhone("(212) 698054317)");
        Customer customer3 = new Customer();
        customer3.setId(2L);
        customer3.setName("Younes Boutikyad");
        customer3.setPhone("(212) 6546545369)");
        customerList.add(customer1);
        customerList.add(customer2);
        customerList.add(customer3);
        return new PageImpl<>(customerList);
    }

    private List<CustomerDto> getCustomerResponseDto() {
        return JsonUtil.fromResourcePathUsingListObjectMapper("/sample-response.json", CustomerDto.class);
    }
}
