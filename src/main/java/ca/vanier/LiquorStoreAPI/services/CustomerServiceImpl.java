package ca.vanier.LiquorStoreAPI.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ca.vanier.LiquorStoreAPI.entity.Customer;
import ca.vanier.LiquorStoreAPI.repository.CustomerRepository;
import ca.vanier.LiquorStoreAPI.validation.CustomerValidator;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer save(Customer customer) {
        //Do the validation before proceed
        CustomerValidator customerValidator = new CustomerValidator();
        ResponseEntity<String> validationResponse = customerValidator.validateCustomer(customer);

        if(validationResponse.getStatusCode()!= HttpStatus.OK){
            throw new IllegalArgumentException(validationResponse.getBody());
        } 

        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return Optional.of(customerRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Customer with ID " + id + " not found")));
    }

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer updateExistingCustomer(Customer updatedCustomer, Long id) {
        // Check if the customer exists
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer with ID " + id + " not found"));

        // Update fields
        existingCustomer.setFirst_name(updatedCustomer.getFirst_name());
        existingCustomer.setLast_name(updatedCustomer.getLast_name());
        existingCustomer.setPhone_number(updatedCustomer.getPhone_number());
        existingCustomer.setAddress(updatedCustomer.getAddress());
        existingCustomer.setDate_of_birth(updatedCustomer.getDate_of_birth());

        CustomerValidator customerValidator = new CustomerValidator();
        ResponseEntity<String> validationResponse = customerValidator.validateCustomer(existingCustomer);

        if(validationResponse.getStatusCode()!= HttpStatus.OK){
            throw new IllegalArgumentException(validationResponse.getBody());
        } 

        // Save and return the updated customer
        return customerRepository.save(existingCustomer);
    }

    @Override
    public void deleteCustomerById(Long id) {

        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            customerRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Customer with ID " + id + " not found");
        }
    }

    

}