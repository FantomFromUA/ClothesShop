package ua.tony.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import ua.tony.dto.UserDto;
import ua.tony.exeption.UserNotFoundException;
import ua.tony.service.UserService;

@RestController
@RequestMapping("/")
@Tag(name = "User", description = "this Controller helps client to interact with Users ")
public class UserRestController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "users", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	@Operation(summary = "Hepls to create new user")
	public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto) {

		return new ResponseEntity<>(userService.save(userDto), HttpStatus.CREATED);
	}

	@RequestMapping(value = "users", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	@Operation(summary = "Helps to update alrady exists user")
	public ResponseEntity<UserDto> updateUser(@RequestBody @Valid UserDto userDto) {

		return new ResponseEntity<>(userService.update(userDto), HttpStatus.CREATED);
	}

	@RequestMapping(value = "users", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	@Operation(summary = "1.Helps to get user by his id " + "2.Helps to get user by his login "
			+ "3.Helps to get all users from DB"
			+ "4. Helps to get all users and their purchases. Enter this parametr: \"pur\" ")
	public ResponseEntity getUsersByIdOrByLoginOrGetAllUsers(
			@RequestParam(value = "user_id", required = false) Integer user_id,
			@RequestParam(value = "user_login", required = false) String user_login,
			@RequestParam(value = "purchases", required = false) String purchases,
			@RequestParam(value = "password", required = false) String password) {
		UserDto  user = null;
		// Find by ID
		if (user_id != null && user_login == null && purchases == null && password == null) {
			user = userService.findById(user_id);
		}

		// Find by login and password
		if (user_id == null && user_login != null && password != null && purchases == null) {
			user = userService.findByLogin(user_login);

			if (!user.getPassword().equals(password)) {
				throw new UserNotFoundException("password is wrong :" + password);
			}
		}
		
		// Find by login
		if (user_id == null && user_login != null && purchases == null && password == null) {
			user = userService.findByLogin(user_login);
		}
		// Find All
		if (user_id == null && user_login == null && purchases == null && password == null)
			return ResponseEntity.ok(userService.findAll());

		// Find user purchases
		if (user_id == null && user_login == null && purchases.equals("pur") && password == null) {
			return ResponseEntity.ok(userService.getUsersAndValueOfPurchases());
		}
		if(user == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
		if(!user.getIsAvaliable()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.ok(user);
	}

	@RequestMapping(value = "users", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	@Operation(summary = "1.Helps to delete user by his id " + "2.Helps to delete all users ")
	public ResponseEntity<HttpStatus> deleteUserByIdOrDeleteAllUsersFromDb(
			@RequestParam(value = "user_id", required = false) Integer user_id) {
		if (user_id != null) {
			userService.deleteById(user_id);
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		} else {
			userService.deleteAll();
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		}
	}
}
