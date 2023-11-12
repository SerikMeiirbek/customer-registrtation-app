package com.serikcode.springbootexample.customer.model;

public record CustomerUpdateRequest(
        String name,
        String email,
        Integer age
) {
}
