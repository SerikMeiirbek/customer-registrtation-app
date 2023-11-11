package com.serikcode.springbootexample.customer;

import com.serikcode.springbootexample.customer.model.Customer;
import com.serikcode.springbootexample.customer.model.CustomerRegistrationRequest;
import com.serikcode.springbootexample.customer.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public List<Customer> getCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("{customerId}")
    public Customer getCustomer(@PathVariable Integer customerId){
        Customer requestedCustomer = customerService.getCustomer(customerId);
        return requestedCustomer;
    }

    @PostMapping
    public void registerCustomer (@RequestBody CustomerRegistrationRequest customerRegistrationRequest){
        customerService.saveCustomer(customerRegistrationRequest);
    }


}
