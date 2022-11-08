package ua.tony.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.tony.dto.OrderDto;
import ua.tony.dto.OrderItemDto;
import ua.tony.dto.ProductDto;
import ua.tony.dto.UserDto;
import ua.tony.service.OrderItemService;
import ua.tony.service.OrderService;
import ua.tony.service.ProductService;
import ua.tony.service.UserService;

@Component
public class DataBaseEntitiesGenerator {

	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderItemService orderItemService;

	public void generateEntities() {
		UserDto user = new UserDto();
		user.setName("tomcat");
		user.setSurname("bober");
		user.setLogin("hello@adad");
		user.setPassword("12345sms");
		user.setCoins(0);
		UserDto user2 = userService.save(user);
		ProductDto product = new ProductDto();
		product.setId(1);
		product.setCode(228);
		product.setDescription("qwertyui");
		product.setInStock(true);
		product.setName("asic");
		product.setType("jeans");
		product.setPrice(555);
		product.setSize("xxl");
		ProductDto product2 = productService.save(product);
		OrderDto order = new OrderDto();
		order.setUserDto(user2);
		OrderDto order2 = orderService.save(order);
		OrderItemDto orderItem = new OrderItemDto();
		orderItem.setOrderDto(order2);
		orderItem.setProductDto(product2);
		orderItemService.save(orderItem);

	}

	public void cleanDataBase() {
		orderItemService.deleteAll();
		productService.deleteAll();
		orderService.deleteAll();
		userService.deleteAll();

	}
}
