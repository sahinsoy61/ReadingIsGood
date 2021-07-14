package com.readingIsGood.ReadingIsGood.Controller;

import com.readingIsGood.ReadingIsGood.Entity.Book;
import com.readingIsGood.ReadingIsGood.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path="/api/book")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

	@Autowired
	BookRepository bookRepository;

	@PostMapping(path="/add")
	public @ResponseBody
	String addNewBook (@RequestParam String name, @RequestParam String author, @RequestParam String serialNo, @RequestParam Integer stock, @RequestParam Double price){
		Book book = new Book();
		book.setName(name);
		book.setAuthor(author);
		book.setSerialNo(serialNo);
		book.setStock(stock);
		book.setPrice(price);

		bookRepository.save(book);


		return "Saved";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Book> getAllBooks (){
		return bookRepository.findAll();
	}

	@GetMapping(path = "/sell/{id}")
	public @ResponseBody String sellBook(@PathVariable Integer id) {
		Optional<Book> book = bookRepository.findById(id);

		if (book.isEmpty()){
			return "Invalid book id";
		}

		book.get().setStock(book.get().getStock() - 1);

		return "Success";
	}
}

