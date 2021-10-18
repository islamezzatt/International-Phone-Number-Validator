package com.example.internationalphonenumbervalidator.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mapstruct.factory.Mappers;

import com.example.internationalphonenumbervalidator.entities.Customer;
import com.example.internationalphonenumbervalidator.model.PhoneNumber;

public class CustomerMapperTest {

    private CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);

    @Test
    public void test_constructPhoneNumber() {
        PhoneNumber phoneNumber = customerMapper.constructPhoneNumber("(212) 6546545369");
        assertEquals("212", phoneNumber.getCountryCode());
        assertEquals("6546545369", phoneNumber.getLocalNumber());
    }

    @Test
    public void test_constructPhoneNumber_null() {
        PhoneNumber phoneNumber = customerMapper.constructPhoneNumber(null);
        assertNull(phoneNumber);
    }

    @Test
    public void test_constructPhoneNumber_emtpyString() {
        PhoneNumber phoneNumber = customerMapper.constructPhoneNumber(null);
        assertNull(phoneNumber);
    }

    @Test
    public void test_extractCountryCode_invalidPhoneNumber() {
        PhoneNumber phoneNumber = customerMapper.constructPhoneNumber("0124312312321");
        assertNull(phoneNumber);
    }

    @Test
    public void test_getPhoneNumberState_valid() {
        Customer customer = new Customer();
        customer.setPhone("(237) 695539786");
        assertEquals("valid", customerMapper.getPhoneNumberState(customer));
    }

    @ParameterizedTest
    @ValueSource(strings = { "asdsadadasdsa", "(212) 6546545369" })
    public void test_getPhoneNumberState_inValid(String arg) {
        Customer customer = new Customer();
        customer.setPhone(arg);
        assertEquals("invalid", customerMapper.getPhoneNumberState(customer));
    }
}
