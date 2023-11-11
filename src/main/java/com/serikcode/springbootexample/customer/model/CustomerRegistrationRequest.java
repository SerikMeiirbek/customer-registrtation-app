package com.serikcode.springbootexample.customer.model;

public record CustomerRegistrationRequest(
        String name,
        String email,
        Integer age
) {
}
