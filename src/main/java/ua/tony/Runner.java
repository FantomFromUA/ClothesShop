package ua.tony;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ua.tony.entity.Product;
import ua.tony.repository.ProductRepository;


@SpringBootApplication
public class Runner implements CommandLineRunner {
    
	@Autowired
	private ProductRepository productRepository;

    public static void main(String[] args) {
    	SpringApplication.run(Runner.class, args);
    	
    }

    @Override
    public void run(String... args) throws Exception {
    	Product product = new Product("Test2", "Jeans", 228, "XXXL", 132.99, "THE BEST FUCKING YEAH");
    	
    	productRepository.save(product);
    	
    	System.out.println("DONE!!!");
    }
}