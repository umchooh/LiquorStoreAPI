package ca.vanier.LiquorStoreAPI.services;

import java.util.Optional;

import ca.vanier.LiquorStoreAPI.entity.Customer;

public interface CustomerService {

    Customer save(Customer customer);

    Optional<Customer> findById (Long id);

    Iterable<Customer> findAll();

    Customer updateExistingCustomer(Customer updatedCustomer, Long id);

    void deleteCustomerById(Long id);

}