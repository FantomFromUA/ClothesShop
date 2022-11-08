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

import ua.tony.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	UserRepository userRepository;

	@BeforeEach
	void beforeEach() {
		userRepository.deleteAll();
	}

	@AfterEach
	void afterEach() {
		userRepository.deleteAll();
	}

	@Test
	public void shouldBeEmpty() {
		List<User> users = userRepository.findAll();

		assertThat(users).isEmpty();
	}

	@Test
	public void addUser() {
		User user = new User();
		user.setId(8);
		user.setName("name");
		user.setSurname("surname");
		user.setPassword("123456");
		user.setLogin("login");
		User user2 = userRepository.save(user);
		assertThat(user2.getLogin()).isEqualTo("login");

	}

	@Test
	public void findUserById() {
		User user = new User();
		user.setName("name");
		user.setSurname("surname");
		user.setPassword("123456");
		user.setLogin("login");
		userRepository.save(user);
		User user3 = userRepository.findByLogin("login");
		User user2 = userRepository.findById(user3.getId()).get();
		assertThat(user2.getLogin()).isEqualTo("login");

	}

	@Test
	public void shouldBeAllEmpty() {
		List<User> users = userRepository.findAll();

		assertThat(users).isEmpty();
	}

	@Test
	public void findUserByLogin() {
		User user = new User();
		user.setName("name");
		user.setSurname("surname");
		user.setPassword("123456");
		user.setLogin("login");
		userRepository.save(user);
		User user2 = userRepository.findByLogin("login");
		assertThat(user2.getLogin()).isEqualTo("login");
		assertThat(user2.getPassword()).isEqualTo("123456");
	}

	@Test
	public void findAllUsers() {
		User user = new User();
		User user2 = new User();
		user.setName("name");
		user.setSurname("surname");
		user.setPassword("123456");
		user.setLogin("login");
		user2.setName("name");
		user2.setSurname("surname");
		user2.setPassword("hiimfromuk");
		user2.setLogin("login2");
		userRepository.save(user);
		userRepository.save(user2);
		assertThat(userRepository.findAll().size()).isEqualTo(2);
	}
}
