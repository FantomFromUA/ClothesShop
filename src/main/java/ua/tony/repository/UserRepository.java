package ua.tony.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ua.tony.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByLogin(String login);

    Optional<User> findById(int id);

    List<User> findAll();

    User save(User user);

    void deleteAll();

    void deleteById(int id);
}
