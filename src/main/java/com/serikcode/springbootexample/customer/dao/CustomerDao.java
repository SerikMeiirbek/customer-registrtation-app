package com.serikcode.springbootexample.customer.dao;

import com.serikcode.springbootexample.customer.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {
    List<Customer> selectAllCustomer();
    Optional<Customer> selectCustomerById(Integer customerId);
    void saveCustomer(Customer customer);
    boolean existsPersonWithEmail(String email);
}
