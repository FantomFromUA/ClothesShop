package ua.tony.mapper;

import org.springframework.stereotype.Component;

import ua.tony.dto.UserDto;
import ua.tony.entity.User;

@Component
public class UserMapper {

	public User convertToEntity(UserDto userDto) {

		User user = new User();
		user.setId(userDto.getId());
		user.setAdminAccess(userDto.getAdminAccess());
		user.setCoins(userDto.getCoins());
		user.setLogin(userDto.getLogin());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		user.setSurname(userDto.getSurname());
		user.setToken(userDto.getToken());
		user.setAvailable(userDto.getIsAvaliable());

		return user;
	}

	public UserDto convertToDto(User user) {

		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setAdminAccess(user.getAdminAccess());
		userDto.setCoins(user.getCoins());
		userDto.setLogin(user.getLogin());
		userDto.setName(user.getName());
		userDto.setPassword(user.getPassword());
		userDto.setSurname(user.getSurname());
		userDto.setToken(user.getToken());
		userDto.setAvaliable(user.isAvailable());

		return userDto;
	}
}
