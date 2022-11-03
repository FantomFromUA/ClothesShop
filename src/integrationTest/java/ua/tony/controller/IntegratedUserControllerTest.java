package ua.tony.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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
import ua.tony.dto.UserDto;
import ua.tony.exeption.UserNotDeletedException;
import ua.tony.exeption.UserNotFoundException;
import ua.tony.service.UserService;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = { Runner.class })
@AutoConfigureMockMvc
public class IntegratedUserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

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

	UserDto user = userService.findByLogin("hello@adad");

	mockMvc.perform(get("/users?user_id=" + user.getId()).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(user)));
    }

    @Test
    public void findUserByLoginAndPassword() throws Exception {

	UserDto user = userService.findByLogin("hello@adad");
	mockMvc.perform(get("/users?user_login=hello@adad&password=12345sms").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(user)));
    }

    @Test
    public void findAllTest() throws Exception {
	UserDto user = userService.findByLogin("hello@adad");
	List<UserDto> users = new ArrayList<>();
	users.add(user);
	mockMvc.perform(get("/users").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		.andExpect(content().json(objectMapper.writeValueAsString(users)));
    }

    @Test
    public void addUserTest() throws Exception {

	UserDto user = userService.findByLogin("hello@adad");

	mockMvc.perform(post("/users").content(objectMapper.writeValueAsString(user)).contentType("application/json"))
		.andExpect(status().isCreated());
    }

    @Test
    public void deleteUser() throws Exception {
	UserDto user = userService.findByLogin("hello@adad");
	mockMvc.perform(
		MockMvcRequestBuilders.delete("/users?user_id=" + user.getId()).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturn404ErrorWhenUserNotFound() throws Exception {

	mockMvc.perform(get("/users?user_id=666").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturn404ErrorWhenUserNotDeleted() throws Exception {

	mockMvc.perform(MockMvcRequestBuilders.delete("/users?user_id=777").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
    }
}
