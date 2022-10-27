package ua.tony.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ua.tony.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	/**
	 * Метод, який знаходить та повертає користувача за заданим логіном
	 * 
	 * @param login - login користувача
	 * @return користувач
	 */
	User findByLogin(String login);

	/**
	 * Метод, який знаходить та повертає користувача за заданим id
	 * 
	 * @param id - id користувача
	 * @return користувач
	 */
	Optional<User> findById(int id);

	/**
	 * Метод, який повертає всі дані з таблиці users в БД
	 * 
	 * @return список користувачів
	 */
	List<User> findAll();

	/**
	 * Метод, який зберігає користувача в БД
	 * 
	 * @param user - користувач
	 * @return користувач
	 */
	User save(User user);

	/**
	 * Метод, який видаляє всі дані з таблиці users в БД
	 */
	void deleteAll();

	/**
	 * Метод, який видаляє користувача за заданим id
	 * 
	 * @param id - id користувача
	 */
	void deleteById(int id);
}
