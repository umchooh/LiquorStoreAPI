package ca.vanier.LiquorStoreAPI.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.vanier.LiquorStoreAPI.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long> {


}

