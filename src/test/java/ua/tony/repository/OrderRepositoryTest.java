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
import ua.tony.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class OrderRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    OrderRepository orderRepository;
    
    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void beforeEach() {
	orderRepository.deleteAll();
    }

    @AfterEach
    void afterEach() {
	orderRepository.deleteAll();
    }

    @Test
    public void shouldBeEmpty() {
	List<Order> orders = orderRepository.findAll();

	assertThat(orders).isEmpty();
    }

    @Test
    public void addOrder() {
	Order order = new Order();
	order.setTotalPrice(345);
	Order order2 = orderRepository.save(order);
	assertThat(order2.getTotalPrice()).isEqualTo(345);

    }

    @Test
    public void findOrderById() {
	Order order = new Order();
	order.setTotalPrice(345);
	orderRepository.save(order);
	Order order2 = orderRepository.findById(4).get();
	assertThat(order2.getTotalPrice()).isEqualTo(345);

    }

    @Test
    public void shouldBeAllEmpty() {
	List<Order> orders = orderRepository.findAll();

	assertThat(orders).isEmpty();
    }

    @Test
    public void findAllOrders() {
	Order order = new Order();
	order.setTotalPrice(345);
	Order order2 = new Order();
	order2.setTotalPrice(345);
	orderRepository.save(order);
	orderRepository.save(order2);
	assertThat(orderRepository.findAll().size()).isEqualTo(2);
    }

    @Test
    public void findOrderWhichRelatedToUser() {
	Order order = new Order();
	order.setTotalPrice(555);
	User user=new User();
	user.setName("name");
	user.setSurname("surname");
	user.setPassword("123456");
	user.setLogin("login");
	userRepository.save(user);
	order.setUser(user);
	order.setCompleted(true);
	orderRepository.save(order);
	List<Order>orders=orderRepository.getOrdersThatRelatedToUser(1);
	assertThat(orders.size()).isEqualTo(1);
	assertThat(orders.get(0).getTotalPrice()).isEqualTo(555);
	userRepository.deleteAll();
    }

}
