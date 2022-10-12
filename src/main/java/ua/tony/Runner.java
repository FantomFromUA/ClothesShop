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
//    	User user1 = new User("Ivan", "Ivanov", "ivan228", "ivanTheBest", 0, false);
//    	User user2 = new User("Tom", "Tomov", "tom228", "tomTheBest", 0, false);
//    	User user3 = new User("Bob", "Bobov", "bob228", "tomTheBest", 0, false);
//    	
//    	
//    	userRepository.save(user1);
//    	userRepository.save(user2);
//    	userRepository.save(user3);
    	
//    	User user = userRepository.findById(1).get();
//    	System.out.println("123");
//    	
//    	Order order = new Order();
//    	order.setUser(user);
//    	
//    	orderRepository.save(order);
    	
//    	Order order = orderRepository.findById(3).get();
    	orderRepository.deleteById(8);
    	
//    	userRepository.deleteById(3);
    	
    	System.out.println("DONE!!!");
    }
}