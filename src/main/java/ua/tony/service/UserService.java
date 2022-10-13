package ua.tony.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.tony.dto.ProductDto;
import ua.tony.dto.UserDto;
import ua.tony.entity.User;
import ua.tony.mapper.UserMapper;
import ua.tony.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserMapper userMapper;
	@Autowired
	UserRepository userRepo;

	public UserDto save(UserDto userDto) {

		User user = userMapper.convertToEntity(userDto);
		return userMapper.convertToDto(userRepo.save(user));
	}

	public UserDto update(UserDto userDto) {

		User user = userMapper.convertToEntity(userDto);
		return userMapper.convertToDto(userRepo.save(user));
	}

	public UserDto findById(Integer id) {

		return userMapper.convertToDto(userRepo.findById(id).get());
	}

	public UserDto findByLogin(String login) {

		return userMapper.convertToDto(userRepo.findByLogin(login));
	}

	public List<UserDto> findAll() {

		List<UserDto> users = userRepo.findAll().stream().map(x -> userMapper.convertToDto(x)).toList();
		return users;
	}

	public void deleteAll() {

		userRepo.deleteAll();
	}

	public void deleteById(Integer id) {

		userRepo.deleteById(id);
	}
}
