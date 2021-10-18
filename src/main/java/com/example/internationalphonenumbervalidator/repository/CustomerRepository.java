package com.example.internationalphonenumbervalidator.repository;

import com.example.internationalphonenumbervalidator.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
