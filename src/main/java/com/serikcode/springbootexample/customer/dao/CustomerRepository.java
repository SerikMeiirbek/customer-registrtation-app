package com.serikcode.springbootexample.customer.dao;

import com.serikcode.springbootexample.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
    boolean existsCustomerByEmail(String email);
}
