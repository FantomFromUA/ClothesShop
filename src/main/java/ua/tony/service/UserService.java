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

    /**
     * Метод, який зберігає користувача в БД
     * 
     * @param userDto - користувач
     * @return користувач
     */
    public UserDto save(UserDto userDto) {

	User user = userMapper.convertToEntity(userDto);
	return userMapper.convertToDto(userRepo.save(user));
    }

    /**
     * Метод, який оновлює інформацію про користувача в БД
     * 
     * @param userDto - користувач
     * @return користувач
     */
    public UserDto update(UserDto userDto) {

	User user = userMapper.convertToEntity(userDto);
	return userMapper.convertToDto(userRepo.save(user));
    }

    /**
     * Метод, який знаходить та повертає користувача за заданим id
     * 
     * @param id - id користувача
     * @return користувач
     */
    public UserDto findById(Integer id) throws UserNotFoundException {

	if (userRepo.findById(id).isPresent())
	    return userMapper.convertToDto(userRepo.findById(id).get());
	else
	    throw new UserNotFoundException("user not found by this id: " + id.toString());
    }

    /**
     * Метод, який знаходить та повертає користувача за заданим логіном
     * 
     * @param login - login користувача
     * @return користувач
     */
    public UserDto findByLogin(String login) throws UserNotFoundException {

	if (userRepo.findByLogin(login)!=null)
	    return userMapper.convertToDto(userRepo.findByLogin(login));
	else
	    throw new UserNotFoundException("user is not found by this login: " + login);
    }

    /**
     * Метод, який повертає всі дані з таблиці users в БД
     * 
     * @return список користувачів
     */
    public List<UserDto> findAll() throws UserNotFoundException  {
        if(userRepo.findAll().size()>0) {
	List<UserDto> users = userRepo.findAll().stream().map(x -> userMapper.convertToDto(x)).toList();
	return users;
        }
        else
            throw new UserNotFoundException("users are not found in DB");
    }

    /**
     * Метод, який видаляє всі дані з таблиці users в БД
     */
    public void deleteAll() {

	userRepo.deleteAll();
    }

    /**
     * Метод, який видаляє користувача за заданим id
     * 
     * @param id - id користувача
     */
    public void deleteById(Integer id) throws UserNotDeletedException {

	try {
	    userRepo.deleteById(id);
	} catch (EmptyResultDataAccessException exc) {
	    throw new UserNotDeletedException("user is not deleted by this id:" + id.toString());
	}
    }

    /**
     * Метод, який рахує загальну ціну всіх покупок заданого користувача
     * 
     * @param userId - id користувача
     * @return загальна ціна покупок
     */
    public Double getPriceOfAllProductsWhichUserBought(Integer userId) throws UserNotFoundException {
        
	if(userRepo.findById(userId).isPresent()) {
	List<Order> orders = orderRepo.getOrdersThatRelatedToUser(userId);
	Double TotalPrice = orders.stream().mapToDouble(x -> x.getTotalPrice()).average().orElse(0);
	return TotalPrice;
	}
	else 
	    throw new UserNotFoundException("user does not exist by this id: "+userId.toString());
	
    }

    /**
     * Метод, який повертає список користувачів та загальну ціну покупок цих
     * користувачів
     * 
     * @return список користувачів та загальну ціну покупок цих користувачів
     */
    public Map<UserDto, Double> getUsersAndValueOfPurchases() {

	List<UserDto> users = findAll();
	Map<UserDto, Double> usersValueOfPurchases = new LinkedHashMap<>();
	for (int i = 0; i < users.size(); i++) {
	    usersValueOfPurchases.put(users.get(i), getPriceOfAllProductsWhichUserBought(users.get(i).getId()));
	}
	return usersValueOfPurchases;
    }
}
