package com.readingIsGood.ReadingIsGood.Controller;


import com.readingIsGood.ReadingIsGood.Entity.Book;
import com.readingIsGood.ReadingIsGood.Entity.Order;
import com.readingIsGood.ReadingIsGood.Repository.BookRepository;
import com.readingIsGood.ReadingIsGood.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.util.Optional;

@Controller
@RequestMapping(path="/api/statistics")
@CrossOrigin(origins = "http://localhost:3000")
public class StatisticsController {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	BookRepository bookRepository;

	@GetMapping(path = "/orderCount/{month}")
	public @ResponseBody Integer totalOrderCount(@PathVariable Integer month){

		Iterable<Order> orders = orderRepository.findAll();

		Integer orderCounter = 0;

		for (Order order : orders) {

			if (order.getDate().toInstant().atZone(ZoneId.of("+3")).getMonthValue() == month){
				orderCounter++;
			}
		}

		return orderCounter;
	}

	@GetMapping(path = "/bookCount/{month}")
	public @ResponseBody Integer totalBookCount(@PathVariable Integer month){

		Iterable<Order> orders = orderRepository.findAll();

		Integer bookCounter = 0;

		for (Order order : orders) {

			if (order.getDate().toInstant().atZone(ZoneId.of("+3")).getMonthValue() == month){
				bookCounter += order.getAmount();
			}
		}

		return bookCounter;
	}

	@GetMapping(path = "/purchasedAmount/{month}")
	public @ResponseBody Double totalPurchasedAmount(@PathVariable Integer month){

		Iterable<Order> orders = orderRepository.findAll();

		Double purchasedAmount = 0.d;

		for (Order order : orders) {

			if (order.getDate().toInstant().atZone(ZoneId.of("+3")).getMonthValue() == month){
				Optional<Book> book = bookRepository.findById(order.getBookId());
				if (book.isPresent()){
					purchasedAmount += order.getAmount() * book.get().getPrice();
				}
			}
		}

		return purchasedAmount;
	}

}
