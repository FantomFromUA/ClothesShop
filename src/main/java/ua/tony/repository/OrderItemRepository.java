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
	OrderItem findByProductId(int productId);

	OrderItem findByOrderId(int orderId);

	Optional<OrderItem> findById(int id);

	Iterable<OrderItem> findAll();

	OrderItem save(OrderItem orderItem);

	void deleteAll();

	void deleteById(int id);

	public @Query(value = "SELECT order_items.id,order_items.product_id,order_items.order_id FROM order_items "
			+ "WHERE order_items.order_id=:orderId ", nativeQuery = true) List<OrderItem> getOrderItemsThatRelatedToOrder(
					@Param("orderId") Integer orderId);
}
