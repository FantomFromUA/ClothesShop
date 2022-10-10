package ua.tony;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ua.tony.entity.*;
import ua.tony.repository.*;


@SpringBootApplication
public class Runner implements CommandLineRunner {
    
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

    public static void main(String[] args) {
    	SpringApplication.run(Runner.class, args);
    	
    }

    @Override
    public void run(String... args) throws Exception {
    	
////    	USER CREATION
//    	User user1 = new User("Ivan", "Ivanov", "ivan228", "ivanTheBest", 0, false);
//    	User user2 = new User("Tom", "Tomov", "tom228", "tomTheBest", 0, false);
//    	User user3 = new User("Bob", "Bobov", "bob228", "tomTheBest", 0, false);
//    	
//    	userRepository.save(user1);
//    	userRepository.save(user2);
//    	userRepository.save(user3);
    	
    	
////    	ORDER CREATION
//    	User user = userRepository.findById(7).get();
//    	
//    	Order order = new Order();
//    	order.setUser(user);
//    	
//    	orderRepository.save(order);
    	
    	
////		ORDER DELETE	
//    	orderRepository.deleteById(7);

    	
////		PRODUCT CREATION	
//    	Product product1 = new Product("Red Jeans", "Jeans", 234, "XXL", 345.1, "test1");
//    	Product product2 = new Product("Blue Jeans", "Jeans", 434, "XL", 355.1, "test2");
//    	Product product3 = new Product("White Shirt", "Shirt", 235, "XL", 347.1, "test3");
//    	
//    	productRepository.save(product1);
//    	productRepository.save(product2);
//    	productRepository.save(product3);
    	
    	
////		ORDER ITEM CREATION
//    	
//    	Product product = productRepository.findById(1).get();
//		Order order = orderRepository.findById(6).get();
//	
//		OrderItem orderItem = new OrderItem(order, product);
//	
//		orderItemRepository.save(orderItem);    	
    	
    	
////		ORDER ITEM DELETE	
//    	orderItemRepository.deleteById(1);
    	
    	
////		SOME SHIT
//    	Order order = orderRepository.findById(6).get();
//    	System.out.println(order.getOrderItems().size());
    	
    	System.out.println("DONE!!!");
    }
}