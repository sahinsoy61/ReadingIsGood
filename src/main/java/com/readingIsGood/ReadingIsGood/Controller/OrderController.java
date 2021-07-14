package com.readingIsGood.ReadingIsGood.Controller;

import com.readingIsGood.ReadingIsGood.DTO.DateDTO;
import com.readingIsGood.ReadingIsGood.Entity.Book;
import com.readingIsGood.ReadingIsGood.Entity.Customer;
import com.readingIsGood.ReadingIsGood.Entity.Order;
import com.readingIsGood.ReadingIsGood.Repository.BookRepository;
import com.readingIsGood.ReadingIsGood.Repository.CustomerRepository;
import com.readingIsGood.ReadingIsGood.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping(path="/api/order")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	BookRepository bookRepository;

	@PostMapping(path="/add")
	public @ResponseBody
	String addNewOrder (@RequestParam Integer customerId, @RequestParam Integer amount, @RequestParam Integer bookId){
		Book book;

		Optional<Customer> O_customer = customerRepository.findById(customerId);
		Optional<Book> O_book = bookRepository.findById(bookId);

		if (O_customer.isEmpty()){
			return "Invalid customer id";
		}

		if (O_book.isEmpty()){
			return "Invalid book id";
		}

		book = O_book.get();

		if (book.getStock() < 1){
			return "Book is out of stock";
		}

		Order order = new Order();
		order.setCustomerId(customerId);
		order.setAmount(amount);
		order.setDate(new Date());
		order.setBookId(bookId);
		order.setCost(amount * book.getPrice());

		book.setStock(book.getStock() - 1);


		orderRepository.save(order);

		return "Saved";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Order> getAllOrders (){
		return orderRepository.findAll();
	}


	@PostMapping(path = "/orders/date/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Order> getAllOrdersBetweenTwoDates (@RequestBody DateDTO payload){

		Date startDate = payload.getStartDate();
		Date endDate = payload.getEndDate();

		Iterable<Order> orders = orderRepository.findAll();
		List<Order> responseOrders = new ArrayList<Order>();

		for (Order order : orders){
			if (order.getDate().after(startDate) && order.getDate().before(endDate)){
				responseOrders.add(order);
			}
		}

		return responseOrders;
	}
}
