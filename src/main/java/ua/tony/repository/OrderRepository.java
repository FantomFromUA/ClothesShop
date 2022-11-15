package ua.tony.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.tony.entity.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

	/**
	 * Метод, який знаходить закази за заданою датою створення заказу
	 * 
	 * @param orderDate - дата створення заказу
	 * @return список заказів
	 */
	List<Order> findByOrderDate(LocalDate orderDate);

	/**
	 * Метод, який знаходить закази за заданою датою доставки заказу
	 * 
	 * @param deliveryDate - дата доставки заказу
	 * @return список заказів
	 */
	List<Order> findByDeliveryDate(LocalDate deliveryDate);

	/**
	 * Метод, який знаходить та повертає заказ за заданим id
	 * 
	 * @param id - id заказу
	 * @return заказ
	 */
	Optional<Order> findById(int id);

	/**
	 * Метод, який повертає всі дані з таблиці orders в БД
	 * 
	 * @return список заказів
	 */
	List<Order> findAll();

	/**
	 * Метод, який зберігає Order в БД
	 * 
	 * @return заказ
	 */
	Order save(Order order);

	/**
	 * Метод, який видаляє всі дані з таблиці orders в БД
	 */
	void deleteAll();

	/**
	 * Метод, який видаляє заказ за заданим id
	 * 
	 * @param id - id заказу
	 */
	void deleteById(int id);

	/**
	 * Метод, який повертає список заказів, який відноситься до заданого користувача
	 * 
	 * @param userId - id користувача
	 * @return список заказів
	 */
	public @Query(value = "SELECT orders.id,orders.total_price,orders.order_date,orders.delivery_date,orders.completed,orders.user_id FROM orders "
			+ "WHERE orders.user_id=:userId AND orders.completed=true ", nativeQuery = true) List<Order> getOrdersThatRelatedToUser(
					@Param("userId") Integer userId);
}
