package ua.tony.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.tony.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

	/**
	 * Метод, який знаходить та повертає список продуктів за заданою назвою
	 * 
	 * @param type - назва продукта
	 * @return список продуктів
	 */
	List<Product> findByName(String name);

	/**
	 * Метод, який знаходить та повертає список продуктів за заданим типом
	 * 
	 * @param type - тип продукта
	 * @return список продуктів
	 */
	List<Product> findByType(String type);

	/**
	 * Метод, який знаходить та повертає продукт за заданим id
	 * 
	 * @param id - id продукта
	 * @return продукт
	 */
	Optional<Product> findById(int id);

	/**
	 * Метод, який повертає всі дані з таблиці products в БД
	 * 
	 * @return список продуктів
	 */
	List<Product> findAll();

	/**
	 * Метод, який зберігає продукт в БД
	 * 
	 * @return продукт
	 */
	Product save(Product product);

	/**
	 * Метод, який видаляє всі дані з таблиці products в БД
	 */
	void deleteAll();

	/**
	 * Метод, який видаляє продукт за заданим id
	 * 
	 * @param id - id продукта
	 */
	void deleteById(int id);

	// ???
	@Query(value = "SELECT products.id,products.name,products.type,products.code,products.size,products.price,products.description,products.in_stock  FROM products "
			+ "JOIN order_items ON order_items.product_id=products.id "
			+ "JOIN orders ON orders.id=order_items.order_id " + "WHERE orders.user_id=:userId ", nativeQuery = true)
	List<Product> getProductsThatBoughtUser(@Param("userId") Integer userId);

	/**
	 * Метод, який повертає список продуктів заданого типу посортованих за
	 * зростанням ціни
	 * 
	 * @param type - тип продукта
	 * @return список продуктів
	 */
	@Query(value = "SELECT products.id,products.name,products.type,products.code,products.size,products.price,products.description,products.in_stock  FROM products "
			+ "WHERE products.type=:type AND products.in_stock=1 " + "ORDER BY price ", nativeQuery = true)
	List<Product> getListOfProductsSortedByPrice(@Param("type") String type);

	/**
	 * Метод, який повертає список продуктів заданого типу посортованих за спаданням
	 * ціни
	 * 
	 * @param type - тип продукта
	 * @return список продуктів
	 */
	@Query(value = "SELECT products.id,products.name,products.type,products.code,products.size,products.price,products.description,products.in_stock  FROM products "
			+ "WHERE products.type=:type AND products.in_stock=1 " + "ORDER BY price DESC ", nativeQuery = true)
	List<Product> getListOfProductsSortedByPriceDESC(@Param("type") String type);
}
