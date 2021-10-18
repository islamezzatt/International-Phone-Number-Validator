package com.example.internationalphonenumbervalidator.utils;

import com.example.internationalphonenumbervalidator.model.PhoneNumber;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class PhoneNumberUtilTest {

    @Test
    public void test_extractCountryCode() {
        PhoneNumber phoneNumber = PhoneNumberUtil.splitCountryCodeAndNumber("(212) 6546545369");
        assertEquals("212", phoneNumber.getCountryCode());
        assertEquals("6546545369", phoneNumber.getLocalNumber());
    }

    @Test
    public void test_extractCountryCode_null() {
        PhoneNumber phoneNumber = PhoneNumberUtil.splitCountryCodeAndNumber(null);
        assertNull(phoneNumber);
    }

    @Test
    public void test_extractCountryCode_emtpyString() {
        PhoneNumber phoneNumber = PhoneNumberUtil.splitCountryCodeAndNumber(null);
        assertNull(phoneNumber);
    }

    @Test
    public void test_extractCountryCode_invalidPhoneNumber() {
        PhoneNumber phoneNumber = PhoneNumberUtil.splitCountryCodeAndNumber("0124312312321");
        assertNull(phoneNumber);
    }


    @Test
    public void test_isValidPhoneNumber_valid(){
        assertTrue(PhoneNumberUtil.isValidPhoneNumber("(237) 695539786"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"asdsadadasdsa", "(212) 6546545369"})
    public void test_isValidPhoneNumber_inValid(String arg){
        assertFalse(PhoneNumberUtil.isValidPhoneNumber(arg));
    }
}
