package ua.tony.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ua.tony.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
	
	Product save(Product product);
}
