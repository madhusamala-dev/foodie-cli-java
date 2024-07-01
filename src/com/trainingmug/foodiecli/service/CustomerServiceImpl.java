package com.trainingmug.foodiecli.service;

import com.trainingmug.foodiecli.exceptions.CustomerAlreadyExistsException;
import com.trainingmug.foodiecli.exceptions.CustomerNotFoundException;
import com.trainingmug.foodiecli.model.Customer;
import com.trainingmug.foodiecli.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerService{

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer customer) throws CustomerAlreadyExistsException{
        Optional<Customer> customerByEmail = this.customerRepository.getCustomerByEmail(customer.getEmail());
        if(customerByEmail.isEmpty())
            throw new CustomerAlreadyExistsException("Customer Already Exists with this email");
        return this.customerRepository.saveCustomer(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return null;
    }

    @Override
    public Customer getCustomerById(String id) throws CustomerNotFoundException {
        return null;
    }

    @Override
    public Customer updateCustomer(Customer customer) throws CustomerNotFoundException {
        return null;
    }

    @Override
    public void deleteCustomer(String id) throws CustomerNotFoundException {

    }
}