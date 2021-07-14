package com.readingIsGood.ReadingIsGood.Repository;


import com.readingIsGood.ReadingIsGood.Entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer> {

}
