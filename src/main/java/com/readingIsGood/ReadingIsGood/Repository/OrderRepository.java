package com.readingIsGood.ReadingIsGood.Repository;

import com.readingIsGood.ReadingIsGood.Entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface OrderRepository extends CrudRepository<Order, Integer> {

}
