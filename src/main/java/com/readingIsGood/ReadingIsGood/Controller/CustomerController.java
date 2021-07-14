package com.readingIsGood.ReadingIsGood.Controller;

import com.readingIsGood.ReadingIsGood.Entity.Customer;
import com.readingIsGood.ReadingIsGood.Entity.Order;
import com.readingIsGood.ReadingIsGood.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path="/api/customer")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	OrderController orderController;

	@PostMapping(path="/add")
	public @ResponseBody
	String addNewCustomer (@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email){
		Customer customer = new Customer();
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setEmail(email);
		customer.setRegistrationDate(new Date());

		customerRepository.save(customer);

		return "Saved";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Customer> getAllCustomers (){
		return customerRepository.findAll();
	}

	@GetMapping(path = "/orders/{id}")
	public @ResponseBody List<Order> getAllOrdersOfCustomer (@PathVariable Integer id){
		Optional<Customer> customer = customerRepository.findById(id);
		List<Order> customerOrders = new ArrayList<Order>();

		if (customer.isEmpty()){
			return customerOrders;
		}

		Iterable<Order> orders = orderController.getAllOrders();

		for (Order order : orders){
			if (order.getCustomerId().equals(customer.get().getId())){
				customerOrders.add(order);
			}
		}

		return customerOrders;
	}

}
