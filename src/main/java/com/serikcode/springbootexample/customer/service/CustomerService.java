package com.serikcode.springbootexample.customer.service;

import com.serikcode.springbootexample.customer.model.Customer;
import com.serikcode.springbootexample.customer.dao.CustomerDao;
import com.serikcode.springbootexample.customer.model.CustomerRegistrationRequest;
import com.serikcode.springbootexample.customer.model.CustomerUpdateRequest;
import com.serikcode.springbootexample.exception.DuplicateResourceException;
import com.serikcode.springbootexample.exception.RequestValidationException;
import com.serikcode.springbootexample.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerDao customerDao;

    public CustomerService(@Qualifier("jpa") CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public List<Customer> getAllCustomers(){
        return customerDao.selectAllCustomer();
    }

    public Customer getCustomer(Integer id){
        return customerDao.selectCustomerById(id)
                .orElseThrow(() -> new ResourceNotFoundException("customer {%s} not found".formatted(id)));
    }

    public void saveCustomer(CustomerRegistrationRequest customerRegistrationRequest){

        //Check if already email is taken
        if (customerDao.existsPersonWithEmail(customerRegistrationRequest.email())){
            throw new DuplicateResourceException("Customer with {%s} email already exists".formatted(customerRegistrationRequest.email()));
        }
        customerDao.saveCustomer(
                new Customer(
                        customerRegistrationRequest.name(),
                        customerRegistrationRequest.email(),
                        customerRegistrationRequest.age()
                )
        );
    }

    public void deleteCustomerById(Integer customerId) {
        if(!customerDao.existsPersonWithId(customerId)){
            throw new ResourceNotFoundException("Customer with id {%s} not found".formatted(customerId));
        }
        customerDao.deleteCustomerById(customerId);
    }

    public void update(Integer customerId, CustomerUpdateRequest updateRequest) {
        Customer customer = getCustomer(customerId);

        boolean changes = false;

        if (updateRequest.name() != null && !updateRequest.name().equals(customer.getName())) {
            customer.setName(updateRequest.name());
            changes = true;
        }

        if (updateRequest.age() != null && !updateRequest.age().equals(customer.getAge())) {
            customer.setAge(updateRequest.age());
            changes = true;
        }

        if (updateRequest.email() != null && !updateRequest.email().equals(customer.getEmail())) {
            if (customerDao.existsPersonWithEmail(updateRequest.email())) {
                throw new DuplicateResourceException(
                        "email already taken"
                );
            }
            customer.setEmail(updateRequest.email());
            changes = true;
        }

        if (!changes) {
            throw new RequestValidationException("no data changes found");
        }

        customerDao.updateCustomer(customer);
    }
}
