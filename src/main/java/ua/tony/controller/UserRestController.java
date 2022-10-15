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
import ua.tony.dto.ProductDto;
import ua.tony.dto.UserDto;
import ua.tony.service.UserService;

@RestController
@RequestMapping("/")
@Tag(name = "User", description = "this Controller helps client to interact with Users ")
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "users", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto) {

	return new ResponseEntity<>(userService.save(userDto), HttpStatus.CREATED);
    }

    @RequestMapping(value = "users", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
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
	    @RequestParam(value = "purchases", required = false) String purchases) {
	if (user_id != null && user_login == null && purchases == null) {

	    List<UserDto> user = new ArrayList<>();
	    user.add(userService.findById(user_id));
	    return ResponseEntity.ok(user);
	}
	if (user_id == null && user_login != null && purchases == null) {

	    List<UserDto> user = new ArrayList<>();
	    user.add(userService.findByLogin(user_login));
	    return ResponseEntity.ok(user);
	}
	if (user_id == null && user_login == null && purchases.equals("pur")) {

	    return ResponseEntity.ok(userService.getUsersAndValueOfPurchases());
	}

	if (user_id == null && user_login == null && purchases == null)
	    return ResponseEntity.ok(userService.findAll());
	else
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
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
