package ua.tony.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import ua.tony.Runner;
import ua.tony.dto.OrderDto;
import ua.tony.dto.UserDto;
import ua.tony.service.OrderService;
import ua.tony.service.UserService;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = { Runner.class })
@AutoConfigureMockMvc
public class IntegratedOrderControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired 
    private DataBaseEntitiesGenerator generator;

    @BeforeEach
    public void setUp() {
	this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	generator.generateEntities();
    }

    @AfterEach
    public void cleanDb() {
	generator.cleanDataBase();
    }

    @Test
    public void findByIdTest() throws Exception {
        UserDto user=userService.findByLogin("hello@adad");
        List<OrderDto> orders=orderService.getOrdersWhichRelatedToUser(user.getId());
	mockMvc.perform(get("/orders?order_id="+orders.get(0).getId()).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(orders)));
    }

    @Test
    public void getOrdersWhichRelatedToUser() throws Exception {

	 UserDto user=userService.findByLogin("hello@adad");
	        List<OrderDto> orders=orderService.getOrdersWhichRelatedToUser(user.getId());
	mockMvc.perform(get("/orders?user_id="+user.getId()).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(orders)));
    }

    @Test
    public void findAllTest() throws Exception {
	UserDto user = userService.findByLogin("hello@adad");
	  List<OrderDto> orders=orderService.getOrdersWhichRelatedToUser(user.getId());
	mockMvc.perform(get("/orders").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		.andExpect(content().json(objectMapper.writeValueAsString(orders)));
    }

    @Test
    public void addOrderTest() throws Exception {

	UserDto user = userService.findByLogin("hello@adad");
	  List<OrderDto> orders=orderService.getOrdersWhichRelatedToUser(user.getId());
	mockMvc.perform(post("/orders").content(objectMapper.writeValueAsString(orders.get(0))).contentType("application/json"))
		.andExpect(status().isCreated());
    }

    @Test
    public void shouldReturn404ErrorWhenOrderNotFound() throws Exception {

	mockMvc.perform(get("/orders?order_id=666").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturn404ErrorWhenOrderNotDeleted() throws Exception {

	mockMvc.perform(MockMvcRequestBuilders.delete("/orders?order_id=777").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
    }
}
