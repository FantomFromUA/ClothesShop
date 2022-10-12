package ua.tony.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
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
}
		