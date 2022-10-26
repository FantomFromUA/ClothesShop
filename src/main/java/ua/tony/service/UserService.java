package ua.tony.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import ua.tony.dto.UserDto;
import ua.tony.entity.Order;
import ua.tony.entity.User;
import ua.tony.exeption.UserNotDeletedException;
import ua.tony.exeption.UserNotFoundException;
import ua.tony.mapper.UserMapper;
import ua.tony.repository.OrderRepository;
import ua.tony.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRepository userRepo;
    @Autowired
    OrderRepository orderRepo;

    public UserDto save(UserDto userDto) {

	User user = userMapper.convertToEntity(userDto);
	return userMapper.convertToDto(userRepo.save(user));
    }

    public UserDto update(UserDto userDto) {

	User user = userMapper.convertToEntity(userDto);
	return userMapper.convertToDto(userRepo.save(user));
    }

    public UserDto findById(Integer id) throws UserNotFoundException {
	if (userRepo.findById(id).isPresent())
	    return userMapper.convertToDto(userRepo.findById(id).get());
	else {
	    throw new UserNotFoundException("user is not found by this id :" + id.toString());
	}
    }

    public UserDto findByLogin(String login) {
	if (userRepo.findByLogin(login).getLogin()!=null)
	    return userMapper.convertToDto(userRepo.findByLogin(login));
	else {
	    throw new UserNotFoundException("user is not found by this login :" + login);
	}
    }

    public List<UserDto> findAll() {

	List<UserDto> users = userRepo.findAll().stream().map(x -> userMapper.convertToDto(x)).toList();
	return users;
    }

    public Double getPriceOfAllProductsThatUserBought(Integer userId) {

	List<Order> orders = orderRepo.getOrdersThatRelatedToUser(userId);
	Double TotalPrice = orders.stream().mapToDouble(x -> x.getTotalPrice()).average().orElse(0);
	return TotalPrice;
    }

    public Map<UserDto, Double> getUsersAndValueOfPurchases() {

	List<UserDto> users = findAll();
	Map<UserDto, Double> usersValueOfPurchases = new LinkedHashMap<>();
	for (int i = 0; i < users.size(); i++) {
	    usersValueOfPurchases.put(users.get(i), getPriceOfAllProductsThatUserBought(users.get(i).getId()));
	}
	return usersValueOfPurchases;
    }

    public void deleteAll() {

	userRepo.deleteAll();
    }

    public void deleteById(Integer id) throws UserNotDeletedException {
	try {
	    userRepo.deleteById(id);
	} catch (EmptyResultDataAccessException exc) {
	    throw new UserNotDeletedException("user is not deleted by this id :" + id.toString());
	}
    }
}
