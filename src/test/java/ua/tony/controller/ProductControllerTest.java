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
import ua.tony.dto.ProductDto;
import ua.tony.exeption.ProductNotDeletedException;
import ua.tony.exeption.ProductNotFoundException;
import ua.tony.service.ProductService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = { Runner.class })
public class ProductControllerTest {

    
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @InjectMocks
    ProductRestController productController;

    @MockBean
    ProductService productService;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
	this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }
    @Test
    public void findByIdTest() throws Exception {
	ProductDto product = new ProductDto();
	product.setName("tomcat");
	product.setCode(335);
	product.setId(1);
	List<ProductDto> products=new ArrayList<>();
	products.add(product);
	when(productService.findById(1)).thenReturn(product);

	mockMvc.perform(get("/products?product_id=1").accept(MediaType.APPLICATION_JSON))
	       .andExpect(status().isOk())
	       .andExpect(content().json(objectMapper.writeValueAsString(products)));
    }
    
    @Test
    public void findByNameTest() throws Exception {
	ProductDto product = new ProductDto();
	product.setName("tomcat");
	product.setCode(335);
	product.setId(1);
	List<ProductDto> products=new ArrayList<>();
	products.add(product);
	when(productService.findByName("tomcat")).thenReturn(products);

	mockMvc.perform(get("/products?product_name=tomcat").accept(MediaType.APPLICATION_JSON))
	       .andExpect(status().isOk())
	       .andExpect(content().json(objectMapper.writeValueAsString(products)));
    }
    
    @Test
    public void findByTypeTest() throws Exception {
	ProductDto product = new ProductDto();
	product.setName("tomcat");
	product.setCode(335);
	product.setId(1);
	product.setType("jeans");
	List<ProductDto> products=new ArrayList<>();
	products.add(product);
	when(productService.findByType("jeans")).thenReturn(products);
	
	mockMvc.perform(get("/products?product_type=jeans").accept(MediaType.APPLICATION_JSON))
	.andExpect(status().isOk())
	.andExpect(content().json(objectMapper.writeValueAsString(products)));
    }

    @Test
    public void findAllTest() throws Exception {
        ProductDto p1=new ProductDto();
        ProductDto p2=new ProductDto();
        ProductDto p3=new ProductDto();
        p1.setName("tom"); 
        p2.setName("bob"); 
        p3.setName("monster"); 
	List<ProductDto> products = new ArrayList<>();
	products.add(p1);
	products.add(p2);
	products.add(p3);

	when(productService.findAll()).thenReturn(products);

	mockMvc.perform(get("/products")
		.accept(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk())
		.andExpect(content().json(objectMapper.writeValueAsString(products)));
    }
    
    @Test
    public void addProductTest() throws Exception {
	ProductDto product=new ProductDto();
	product.setId(1);
	product.setName("tomfdf");
	product.setCode(333);
	product.setType("hoodie");
	product.setPrice(345);
	product.setSize("xxl");
	when(productService.save(any(ProductDto.class))).thenReturn(product);

	mockMvc.perform(post("/products")
		.content(objectMapper.writeValueAsString(product))
		.contentType("application/json")).andExpect(status().isCreated());
    }
    
    @Test
    public void deleteProduct() throws Exception{
	
      Mockito.doNothing().when(productService).deleteById(1);
      mockMvc.perform(MockMvcRequestBuilders
	            .delete("/products?product_id=1")
	            .accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isNoContent());
    }
    
    @Test
    public void shouldReturn404ErrorWhenProductNotFound() throws Exception{

 	when(productService.findById(1)).thenThrow( new ProductNotFoundException("product is not found by this id:1"));

 	mockMvc.perform(get("/products?product_id=1").accept(MediaType.APPLICATION_JSON))
 	       .andExpect(status().isNotFound());
    }
    
   @Test
    public void shouldReturn404ErrorWhenProductNotDeleted() throws Exception{
	
       
	Mockito.doThrow(new ProductNotDeletedException("product is not deleted by this id:1")).when(productService).deleteById(1);
	 mockMvc.perform(MockMvcRequestBuilders
	            .delete("/products?product_id=1")
	            .accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isNotFound());
    }
}