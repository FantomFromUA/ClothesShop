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

    List<Product> findByName(String name);

    List<Product> findByType(String type);

    Optional<Product> findById(int id);

    List<Product> findAll();

    Product save(Product product);

    void deleteAll();

    void deleteById(int id);

    @Query(value = "SELECT products.id,products.name,products.type,products.code,products.size,products.price,products.description,products.in_stock  FROM products "
	    + "JOIN order_items ON order_items.product_id=products.id "
	    + "JOIN orders ON orders.id=order_items.order_id " + "WHERE orders.user_id=:userId ", nativeQuery = true)
    List<Product> getProductsThatBoughtUser(@Param("userId") Integer userId);

    @Query(value = "SELECT products.id,products.name,products.type,products.code,products.size,products.price,products.description,products.in_stock  FROM products "
	    + "WHERE products.type=:type AND products.in_stock=1 " + "ORDER BY price ", nativeQuery = true)
    List<Product> getSortedListByPriceByGrowthByType(@Param("type") String type);

    @Query(value = "SELECT products.id,products.name,products.type,products.code,products.size,products.price,products.description,products.in_stock  FROM products "
	    + "WHERE products.type=:type AND products.in_stock=1 " + "ORDER BY price DESC ", nativeQuery = true)
    List<Product> getSortedListByPriceByDeclineByType(@Param("type") String type);
}
