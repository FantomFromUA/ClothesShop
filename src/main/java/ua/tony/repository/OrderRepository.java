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
	Iterable<Order> findByOrderDate(LocalDate orderDate);

	Iterable<Order> findByDeliveryDate(LocalDate deliveryDate);

	Optional<Order> findById(int id);

	Iterable<Order> findAll();

	Order save(Order order);

	void deleteAll();

	void deleteById(int id);

	public @Query(value = "SELECT orders.id,orders.total_price,orders.order_date,orders.delivery_date,orders.completed,orders.user_id FROM orders "
			+ "WHERE orders.user_id=:userId ", nativeQuery = true) List<Order> getOrdersThatRelatedToUser(
					@Param("userId") Integer userId);
}
