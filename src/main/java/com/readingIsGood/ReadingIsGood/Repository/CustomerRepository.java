package com.readingIsGood.ReadingIsGood.Repository;

import com.readingIsGood.ReadingIsGood.Entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
