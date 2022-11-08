package ua.tony.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import ua.tony.dto.ProductDto;
import ua.tony.dto.UserDto;
import ua.tony.service.ProductService;
import ua.tony.service.UserService;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = { Runner.class })
@AutoConfigureMockMvc
public class IntegratedProductControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ProductService productService;
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

		List<ProductDto> products = productService.findByName("asic");

		ProductDto product = products.get(0);
		mockMvc.perform(get("/products?product_id=" + product.getId()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(products)));
	}

	@Test
	public void findProductByName() throws Exception {

		List<ProductDto> products = productService.findByName("asic");
		mockMvc.perform(get("/products?product_name=asic").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(products)));
	}

	@Test
	public void findProductByType() throws Exception {

		List<ProductDto> products = productService.findByName("asic");
		mockMvc.perform(get("/products?product_type=jeans").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(products)));
	}

	@Test
	public void getProductsThatBoughtUserTest() throws Exception {
		UserDto user = userService.findByLogin("hello@adad");
		mockMvc.perform(get("/users/products?user_id=" + user.getId()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	public void findAllTest() throws Exception {
		List<ProductDto> products = productService.findByName("asic");
		mockMvc.perform(get("/products").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(products)));
	}

	@Test
	public void addProductTest() throws Exception {

		List<ProductDto> products = productService.findByName("asic");

		mockMvc.perform(post("/products").content(objectMapper.writeValueAsString(products.get(0)))
				.contentType("application/json")).andExpect(status().isCreated());
	}

	@Test
	public void shouldReturn404ErrorWhenProductNotFound() throws Exception {

		mockMvc.perform(get("/prodcuts?product_id=666").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	public void shouldReturn404ErrorWhenProductNotDeleted() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.delete("/products?product_id=777").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
}
