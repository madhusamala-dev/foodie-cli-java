package com.trainingmug.foodiecli.repository;


import com.trainingmug.foodiecli.model.Customer;
import com.trainingmug.foodiecli.util.CsvReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerRepository {

    List<Customer> customersList;

    public CustomerRepository() {
        this.customersList = new ArrayList<>();
        CsvReader csvReader = new CsvReader();
        this.customersList = csvReader.readCustomersFromCsv();
    }

    public List<Customer> getCustomersList() {
        return this.customersList;
    }

    public Customer addCustomer(Customer customer) {
        this.customersList.add(customer);
        return customer;
    }

    public Optional<Customer> getCustomerById(String id) {
        return this.customersList.stream().filter(customer -> customer.getId().equals(id)).findFirst();
    }

    public Optional<Customer> updateCustomer(Customer customerToBeUpdated) {
        return this.customersList.stream().filter(customer -> customer.getId().equals(customerToBeUpdated.getId()))
                .findFirst()
                .map(customer -> {
                    customer.setName(customerToBeUpdated.getName())
                            .setEmail(customerToBeUpdated.getEmail())
                            .setPassword(customerToBeUpdated.getPassword());

                    return customer;
                });
    }

    public void deleteCustomer(Customer customer){
        this.customersList.remove(customer);
    }
}
