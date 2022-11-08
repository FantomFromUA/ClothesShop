package ua.tony.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import ua.tony.entity.Order;
import ua.tony.entity.OrderItem;
import ua.tony.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class OrderItemRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	OrderItemRepository orderItemRepository;

	@BeforeEach
	void beforeEach() {
		orderItemRepository.deleteAll();
		orderRepository.deleteAll();
	}

	@AfterEach
	void afterEach() {
		orderItemRepository.deleteAll();
		orderRepository.deleteAll();
	}

	@Test
	public void shouldBeEmpty() {
		List<OrderItem> orderItems = orderItemRepository.findAll();

		assertThat(orderItems).isEmpty();
	}

	@Test
	public void addOrderItem() {
		OrderItem orderItem = new OrderItem();
		orderItemRepository.save(orderItem);
		assertThat(orderItem.getId()).isEqualTo(3);

	}

	@Test
	public void findOrderItemById() {
		OrderItem orderItem = new OrderItem();
		orderItemRepository.save(orderItem);
		OrderItem orderItem2 = orderItemRepository.findById(4).get();
		assertThat(orderItem2.getId()).isEqualTo(4);

	}

	@Test
	public void shouldBeAllEmpty() {
		List<OrderItem> orderItems = orderItemRepository.findAll();

		assertThat(orderItems).isEmpty();
	}

	@Test
	public void findAllOrderItems() {
		OrderItem orderItem = new OrderItem();
		orderItemRepository.save(orderItem);
		OrderItem orderItem2 = new OrderItem();
		orderItemRepository.save(orderItem2);
		assertThat(orderItemRepository.findAll().size()).isEqualTo(2);
	}

}
