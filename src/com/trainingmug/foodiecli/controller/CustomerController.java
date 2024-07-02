package com.trainingmug.foodiecli.controller;

import com.trainingmug.foodiecli.exceptions.CustomerExistsException;
import com.trainingmug.foodiecli.model.Customer;
import com.trainingmug.foodiecli.service.CustomerServiceImpl;

public class CustomerController {

    private CustomerServiceImpl customerService;

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    public Customer save(Customer customer) throws CustomerExistsException {
        return this.customerService.save(customer);
    }
}
