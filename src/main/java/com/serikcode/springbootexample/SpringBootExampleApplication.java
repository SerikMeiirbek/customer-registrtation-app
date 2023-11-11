package com.serikcode.springbootexample;

import com.serikcode.springbootexample.customer.dao.CustomerRepository;
import com.serikcode.springbootexample.customer.model.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class SpringBootExampleApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SpringBootExampleApplication.class, args);

	}

	@Bean
	CommandLineRunner runner(CustomerRepository customerRepository){
		return args -> {
			Customer customer1 = new Customer(
					"Alex",
					"alex@gmail.com",
					21
			);

			Customer customer2 = new Customer(
					"Jamila",
					"jamila@gmail.com",
					19
			);

			List<Customer> customers = List.of(customer1, customer2);
			customerRepository.saveAll(customers);
		};
	}
}
