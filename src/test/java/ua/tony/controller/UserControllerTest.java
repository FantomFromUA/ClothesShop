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
import ua.tony.dto.UserDto;
import ua.tony.exeption.UserNotDeletedException;
import ua.tony.exeption.UserNotFoundException;
import ua.tony.service.UserService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = { Runner.class })
class UserControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@InjectMocks
	UserRestController userController;

	@MockBean
	UserService userService;
	@Autowired
	private ObjectMapper objectMapper;

	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	@Test
	public void findByIdTest() throws Exception {
		UserDto user = new UserDto();
		user.setName("tomcat");
		user.setId(1);
		when(userService.findById(1)).thenReturn(user);

		mockMvc.perform(get("/users?user_id=1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(user)));
	}

	@Test
	public void findByLoginTest() throws Exception {
		UserDto user = new UserDto();
		user.setName("tomcat");
		user.setId(1);
		user.setLogin("teammate");
		when(userService.findByLogin("teammate")).thenReturn(user);

		mockMvc.perform(get("/users?user_login=teammate").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(user)));
	}

	@Test
	public void findAllTest() throws Exception {
		UserDto u1 = new UserDto();
		UserDto u2 = new UserDto();
		UserDto u3 = new UserDto();
		u1.setName("tom");
		u2.setName("bob");
		u3.setName("monster");
		List<UserDto> users = new ArrayList<>();
		users.add(u1);
		users.add(u2);
		users.add(u3);

		when(userService.findAll()).thenReturn(users);

		mockMvc.perform(get("/users").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(users)));
	}

	@Test
	public void addUserTest() throws Exception {
		UserDto user = new UserDto();
		user.setId(1);
		user.setName("tom");
		user.setSurname("helloman");
		user.setLogin("werqewr@dsad");
		when(userService.save(any(UserDto.class))).thenReturn(user);

		mockMvc.perform(post("/users").content(objectMapper.writeValueAsString(user)).contentType("application/json"))
				.andExpect(status().isCreated());
	}

	@Test
	public void deleteUser() throws Exception {

		Mockito.doNothing().when(userService).deleteById(1);
		mockMvc.perform(MockMvcRequestBuilders.delete("/users?user_id=1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

	@Test
	public void shouldReturn404ErrorWhenUserNotFound() throws Exception {

		when(userService.findById(1)).thenThrow(new UserNotFoundException("user is not found by this id:1"));

		mockMvc.perform(get("/users?user_id=1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}

	@Test
	public void shouldReturn404ErrorWhenUserNotDeleted() throws Exception {

		Mockito.doThrow(new UserNotDeletedException("user is not deleted by this id:1")).when(userService)
				.deleteById(1);
		mockMvc.perform(MockMvcRequestBuilders.delete("/users?user_id=1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

}