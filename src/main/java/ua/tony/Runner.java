package ua.tony;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ua.tony.dto.ProductDto;
import ua.tony.dto.UserDto;
import ua.tony.mapper.OrderItemMapper;
import ua.tony.mapper.OrderMapper;
import ua.tony.mapper.ProductMapper;
import ua.tony.mapper.UserMapper;
import ua.tony.repository.OrderItemRepository;
import ua.tony.repository.OrderRepository;
import ua.tony.repository.ProductRepository;
import ua.tony.repository.UserRepository;
import ua.tony.service.OrderItemService;
import ua.tony.service.OrderService;
import ua.tony.service.ProductService;
import ua.tony.service.UserService;

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

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private OrderItemMapper orderItemMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderItemService orderItemService;

	@Autowired
	private ProductService productService;

	public static void main(String[] args) {
		SpringApplication.run(Runner.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		
	}
}