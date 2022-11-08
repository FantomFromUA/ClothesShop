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
import ua.tony.dto.UserDto;
import ua.tony.exeption.OrderNotDeletedException;
import ua.tony.exeption.OrderNotFoundException;
import ua.tony.service.OrderService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = { Runner.class })
public class OrderControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@InjectMocks
	OrderRestController orderController;

	@MockBean
	OrderService orderService;
	@Autowired
	private ObjectMapper objectMapper;

	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	@Test
	public void findByIdTest() throws Exception {
		OrderDto order = new OrderDto();
		order.setId(1);
		order.setTotalPrice(456);
		when(orderService.findById(1)).thenReturn(order);

		List<OrderDto> orders = new ArrayList<>();
		orders.add(order);

		mockMvc.perform(get("/orders?order_id=1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(orders)));
	}

	@Test
	public void findByUserIdTest() throws Exception {
		OrderDto order = new OrderDto();
		order.setId(1);
		order.setTotalPrice(456);
		List<OrderDto> orders = new ArrayList<>();
		orders.add(order);
		when(orderService.getOrdersWhichRelatedToUser(1)).thenReturn(orders);

		mockMvc.perform(get("/orders?user_id=1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(orders)));
	}

	@Test
	public void findAllTest() throws Exception {
		OrderDto o1 = new OrderDto();
		OrderDto o2 = new OrderDto();
		OrderDto o3 = new OrderDto();
		o1.setId(1);
		o2.setId(2);
		o3.setId(3);
		o1.setTotalPrice(123);
		o2.setTotalPrice(345);
		o3.setTotalPrice(555);
		List<OrderDto> orders = new ArrayList<>();
		orders.add(o1);
		orders.add(o2);
		orders.add(o3);

		when(orderService.findAll()).thenReturn(orders);

		mockMvc.perform(get("/orders").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(orders)));
	}

	@Test
	public void addOrderTest() throws Exception {
		OrderDto order = new OrderDto();
		UserDto user = new UserDto();
		order.setId(1);
		user.setName("tom");
		user.setSurname("helloman");
		user.setLogin("werqewr@dsad");
		order.setUserDto(user);
		when(orderService.save(any(OrderDto.class))).thenReturn(order);

		mockMvc.perform(post("/orders").content(objectMapper.writeValueAsString(order)).contentType("application/json"))
				.andExpect(status().isCreated());
	}

	@Test
	public void deleteOrder() throws Exception {

		Mockito.doNothing().when(orderService).deleteById(1);
		mockMvc.perform(MockMvcRequestBuilders.delete("/orders?order_id=1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

	@Test
	public void shouldReturn404ErrorWhenUserNotFound() throws Exception {

		when(orderService.findById(1)).thenThrow(new OrderNotFoundException("order is not found by this id:1"));

		mockMvc.perform(get("/orders?order_id=1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}

	@Test
	public void shouldReturn404ErrorWhenUserNotDeleted() throws Exception {

		Mockito.doThrow(new OrderNotDeletedException("order is not deleted by this id:1")).when(orderService)
				.deleteById(1);
		mockMvc.perform(MockMvcRequestBuilders.delete("/orders?order_id=1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
}