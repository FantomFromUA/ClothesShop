package ua.tony.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

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
}
