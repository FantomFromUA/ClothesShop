package ua.tony.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.tony.entity.Order;
import ua.tony.entity.OrderItem;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Integer> {

	/**
	 * Метод, який знаходить та повертає елемент заказу за заданим id продукта
	 * 
	 * @param productId - id продукта
	 * @return елемент заказу
	 */
	OrderItem findByProductId(int productId);

	/**
	 * Метод, який знаходить список елементів заказу за заданим id заказу
	 * 
	 * @param orderId - id заказу
	 * @return список елементів заказу
	 */
	List<OrderItem> findByOrderId(int orderId);

	/**
	 * Метод, який знаходить та повертає елемент заказу за заданим id
	 * 
	 * @param id - id елемента заказу
	 * @return елемент заказу
	 */
	Optional<OrderItem> findById(int id);

	/**
	 * Метод, який повертає всі дані з таблиці order_items в БД
	 * 
	 * @return список елементів заказу
	 */
	List<OrderItem> findAll();

	/**
	 * Метод, який зберігає елемент заказу в БД
	 * 
	 * @return елемент заказу
	 */
	OrderItem save(OrderItem orderItem);

	/**
	 * Метод, який видаляє всі дані з таблиці order_items в БД
	 */
	void deleteAll();

	/**
	 * Метод, який видаляє елемент заказу за заданим id
	 * 
	 * @param id - id елемента заказу
	 */
	void deleteById(int id);

	/**
	 * Метод, який повертає список елементів заказу, який відноситься до заданого
	 * заказу
	 * 
	 * @param orderId - id заказу
	 * @return список елементів заказу
	 */
	public @Query(value = "SELECT order_items.id,order_items.product_id,order_items.order_id FROM order_items "
			+ "WHERE order_items.order_id=:orderId ", nativeQuery = true) List<OrderItem> getOrderItemsWhichRelatedToOrder(
					@Param("orderId") Integer orderId);
}
