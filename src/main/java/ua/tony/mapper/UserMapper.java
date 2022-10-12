package ua.tony.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import ua.tony.dto.UserDto;
import ua.tony.entity.User;

@Component
public class UserMapper {

    private OrderMapper orderMapper;

    @Autowired
    public UserMapper(@Lazy OrderMapper orderMapper) {
	this.orderMapper = orderMapper;

    }

    public User convertToEntity(UserDto userDto) {

	User user = new User();
	user.setId(userDto.getId());
	user.setAdminAccess(userDto.getAdminAccess());
	user.setCoins(userDto.getCoins());
	user.setLogin(userDto.getLogin());
	user.setName(userDto.getName());
	user.setPassword(userDto.getPassword());
	user.setSurname(userDto.getSurname());
	user.setOrders(userDto.getOrdersDto().stream().map(x -> orderMapper.convertToEntity(x)).toList());

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
	userDto.setOrdersDto(user.getOrders().stream().map(x -> orderMapper.convertToDto(x)).toList());

	return userDto;
    }
}
