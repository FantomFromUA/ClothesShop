package ua.tony;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ua.tony.entity.Order;
import ua.tony.entity.OrderItem;
import ua.tony.mapper.OrderItemMapper;
import ua.tony.mapper.OrderMapper;
import ua.tony.mapper.ProductMapper;
import ua.tony.mapper.UserMapper;
import ua.tony.repository.OrderItemRepository;
import ua.tony.repository.OrderRepository;
import ua.tony.repository.ProductRepository;
import ua.tony.repository.UserRepository;

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

	/*
	 * Product product=productRepository.findById(1).get();
	 * 
	 * ProductDto productDto=productMapper.convertToDto(product); Product
	 * product1=productMapper.convertToEntity(productDto);
	 * System.out.println(product1.toString());
	 */
	/*
	 * Product product = productRepository.findById(1).get(); User
	 * user=userRepository.findById(3).get(); Order
	 * order=orderRepository.findById(4).get(); OrderItem orderItem=new OrderItem();
	 * orderItem.setProduct(product); orderItem.setOrder(order);
	 * orderItemRepository.save(orderItem);
	 */
	/*User user = userRepository.findById(3).get();
	UserDto urerDto= userMapper.convertToDto(user);
	User user1=userMapper.convertToEntity(urerDto);
	Order order= orderRepository.findById(4).get();
        OrderDto orderDto=orderMapper.convertToDto(order);*/
	List<Order> orders= orderRepository.getOrdersThatRelatedToUser(3);
	List<OrderItem> orderItems=orderItemRepository.getOrderItemsThatRelatedToOrder(4);
	System.out.println(orderItems.get(0).toString());
        
    }
}