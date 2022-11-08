package ua.tony.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import ua.tony.Runner;
import ua.tony.dto.OrderDto;
import ua.tony.dto.OrderItemDto;
import ua.tony.dto.ProductDto;
import ua.tony.dto.UserDto;
import ua.tony.exeption.OrderItemNotDeletedException;
import ua.tony.exeption.OrderItemNotFoundException;
import ua.tony.service.OrderItemService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = { Runner.class })
public class OrderItemControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@InjectMocks
	OrderItemRestController orderItemController;

	@MockBean
	OrderItemService orderItemService;
	@Autowired
	private ObjectMapper objectMapper;

	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	@Test
	public void findByIdTest() throws Exception {
		OrderItemDto orderItem = new OrderItemDto();
		ProductDto product = new ProductDto();
		product.setName("tomcat");
		product.setCode(335);
		product.setId(1);
		orderItem.setProductDto(product);
		when(orderItemService.findById(1)).thenReturn(orderItem);

		List<OrderItemDto> orderItems = new ArrayList<>();
		orderItems.add(orderItem);

		mockMvc.perform(get("/orderItems?order_item_id=1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(orderItems)));
	}

	@Test
	public void findByOrderIdTest() throws Exception {
		OrderItemDto orderItem = new OrderItemDto();
		ProductDto product = new ProductDto();
		product.setName("tomcat");
		product.setCode(335);
		product.setId(1);
		orderItem.setProductDto(product);
		List<OrderItemDto> orderItems = new ArrayList<>();
		orderItems.add(orderItem);
		when(orderItemService.findByOrderId(1)).thenReturn(orderItems);

		mockMvc.perform(get("/orderItems?order_id=1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(orderItems)));
	}

	@Test
	public void findByProductIdTest() throws Exception {
		OrderItemDto orderItem = new OrderItemDto();
		ProductDto product = new ProductDto();
		product.setName("tomcat");
		product.setCode(335);
		product.setId(1);
		orderItem.setProductDto(product);
		List<OrderItemDto> orderItems = new ArrayList<>();
		orderItems.add(orderItem);
		when(orderItemService.findByProductId(1)).thenReturn(orderItem);

		mockMvc.perform(get("/orderItems?product_id=1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(orderItems)));

	}

	@Test
	public void findAllTest() throws Exception {
		OrderItemDto oi1 = new OrderItemDto();
		OrderItemDto oi2 = new OrderItemDto();
		OrderItemDto oi3 = new OrderItemDto();
		oi1.setId(1);
		oi2.setId(2);
		oi3.setId(3);
		List<OrderItemDto> orderItems = new ArrayList<>();
		orderItems.add(oi1);
		orderItems.add(oi2);
		orderItems.add(oi3);

		when(orderItemService.findAll()).thenReturn(orderItems);

		mockMvc.perform(get("/orderItems").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(orderItems)));
	}

	@Test
	public void addProductTest() throws Exception {
		OrderItemDto orderItem = new OrderItemDto();
		ProductDto product = new ProductDto();
		OrderDto order = new OrderDto();
		UserDto user = new UserDto();
		user.setId(1);
		product.setId(1);
		product.setName("tomfdf");
		product.setCode(333);
		product.setType("hoodie");
		product.setPrice(345);
		product.setSize("xxl");
		order.setId(1);
		order.setTotalPrice(332);
		order.setUserDto(user);
		orderItem.setOrderDto(order);
		orderItem.setProductDto(product);
		when(orderItemService.save(any(OrderItemDto.class))).thenReturn(orderItem);

		mockMvc.perform(
				post("/orderItems").content(objectMapper.writeValueAsString(orderItem)).contentType("application/json"))
				.andExpect(status().isCreated());
	}

	@Test
	public void deleteOrderItem() throws Exception {

		Mockito.doNothing().when(orderItemService).deleteById(1);
		mockMvc.perform(MockMvcRequestBuilders.delete("/orderItems?order_item_id=1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

	@Test
	public void shouldReturn404ErrorWhenOrderItemNotFound() throws Exception {

		when(orderItemService.findById(1))
				.thenThrow(new OrderItemNotFoundException("orderItem is not found by this id:1"));

		mockMvc.perform(get("/orderItems?order_item_id=1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	public void shouldReturn404ErrorWhenOrderItemNotDeleted() throws Exception {

		Mockito.doThrow(new OrderItemNotDeletedException("orderItem is not deleted by this id:1"))
				.when(orderItemService).deleteById(1);
		mockMvc.perform(MockMvcRequestBuilders.delete("/orderItems?order_item_id=1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
}
