package ua.tony.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ua.tony.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
	Iterable<Product> findByName(String name);

	Iterable<Product> findByType(String type);

	Optional<Product> findById(int id);

	Iterable<Product> findAll();

	Product save(Product product);

	void deleteAll();

	void deleteById(int id);
}
