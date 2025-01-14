package ca.vanier.LiquorStoreAPI.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.vanier.LiquorStoreAPI.entity.Customer;
import ca.vanier.LiquorStoreAPI.services.CustomerServiceImpl;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @GetMapping("/list")
    public Iterable<Customer> getAllCustomers() {
        return customerServiceImpl.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id ) {
        Optional<Customer> customer = customerServiceImpl.findById(id);
        if (customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/register")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerServiceImpl.save(customer);  
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(
            @PathVariable Long id,
            @RequestBody Customer updatedCustomer) {

        return customerServiceImpl.updateExistingCustomer(updatedCustomer, id);
        
    }

    @DeleteMapping("remove/{id}")
    public void deleteCustomerById(@PathVariable Long id) {
        customerServiceImpl.deleteCustomerById(id);
    }
}