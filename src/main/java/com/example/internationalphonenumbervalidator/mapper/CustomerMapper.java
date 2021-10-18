package com.example.internationalphonenumbervalidator.mapper;

import java.util.List;

import com.example.internationalphonenumbervalidator.model.CustomerDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.example.internationalphonenumbervalidator.entities.Customer;
import com.example.internationalphonenumbervalidator.enums.CountryCodeEnum;
import com.example.internationalphonenumbervalidator.model.PhoneNumber;
import com.example.internationalphonenumbervalidator.utils.PhoneNumberUtil;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {

    @AfterMapping
    default void addCountryName(@MappingTarget List<CustomerDto> customerDtoList) {
        customerDtoList.stream().forEach(customerResponseDto -> {
            String countryName = CountryCodeEnum
                    .findByCountryCode(customerResponseDto.getPhoneNumber().getCountryCode()).getCountryName();
            customerResponseDto.setCountry(countryName);
        });
    }

    List<CustomerDto> fromCustomerEntityList(List<Customer> customerList);

    @Mapping(source = "phone", target = "phoneNumber")
    @Mapping(source = "customer", target = "state", qualifiedByName = "getState")
    CustomerDto fromCustomerEntity(Customer customer);

    default PhoneNumber constructPhoneNumber(String phoneNumber) {
        return PhoneNumberUtil.splitCountryCodeAndNumber(phoneNumber);
    }

    @Named("getState")
    default String getPhoneNumberState(Customer customer) {
        return PhoneNumberUtil.isValidPhoneNumber(customer.getPhone()) ? "valid" : "invalid";
    }

}
