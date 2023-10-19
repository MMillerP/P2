package com.CustomerDatabase.repository;

import com.CustomerDatabase.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer,String> {}
