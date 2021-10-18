package com.example.internationalphonenumbervalidator.mapper;

import com.example.internationalphonenumbervalidator.entities.Customer;
import com.example.internationalphonenumbervalidator.model.CustomerDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-18T17:49:31+0200",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public List<CustomerDto> fromCustomerEntityList(List<Customer> customerList) {
        if ( customerList == null ) {
            return null;
        }

        List<CustomerDto> list = new ArrayList<CustomerDto>( customerList.size() );
        for ( Customer customer : customerList ) {
            list.add( fromCustomerEntity( customer ) );
        }

        addCountryName( list );

        return list;
    }

    @Override
    public CustomerDto fromCustomerEntity(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDto customerDto = new CustomerDto();

        customerDto.setPhoneNumber( constructPhoneNumber( customer.getPhone() ) );
        customerDto.setState( getPhoneNumberState( customer ) );
        customerDto.setName( customer.getName() );

        return customerDto;
    }
}
