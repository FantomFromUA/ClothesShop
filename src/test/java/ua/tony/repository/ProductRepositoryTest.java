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

import ua.tony.entity.Product;
import ua.tony.entity.Product;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProductRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void beforeEach() {
	productRepository.deleteAll();
    }

    @AfterEach
    void afterEach() {
	productRepository.deleteAll();
    }

    @Test
    public void shouldBeEmpty() {
	List<Product> products = productRepository.findAll();

	assertThat(products).isEmpty();
    }

    @Test
    public void addUser() {
	Product prodcut = new Product();
	prodcut.setName("name");
	Product product2 = productRepository.save(prodcut);
	assertThat(product2.getName()).isEqualTo("name");

    }

    @Test
    public void findProductById() {
	Product product = new Product();
	product.setName("name");
	productRepository.save(product);
	Product product2 = productRepository.findById(6).get();
	assertThat(product2.getName()).isEqualTo("name");

    }

    @Test
    public void shouldBeAllEmpty() {
	List<Product> products = productRepository.findAll();

	assertThat(products).isEmpty();
    }

    @Test
    public void findProductsByName() {
	Product product = new Product();
	product.setName("name");
	product.setPrice(337);
	productRepository.save(product);
	List<Product> products = productRepository.findByName("name");
	assertThat(products.get(0).getName()).isEqualTo("name");
	assertThat(products.get(0).getPrice()).isEqualTo(337);
    }
    @Test
    public void findProductsByType() {
	Product product = new Product();
	product.setName("name");
	product.setPrice(337);
	product.setType("jeans");
	productRepository.save(product);
	List<Product> products = productRepository.findByType("jeans");
	assertThat(products.get(0).getName()).isEqualTo("name");
	assertThat(products.get(0).getPrice()).isEqualTo(337);
	assertThat(products.get(0).getType()).isEqualTo("jeans");
    }

    @Test
    public void findAllProducts() {
	Product product = new Product();
	Product product2 = new Product();
	product.setName("name");
	product.setPrice(337);
	product.setType("jeans");
	product2.setName("name1");
	product2.setPrice(3371);
	product2.setType("jeans");
	productRepository.save(product);
	productRepository.save(product2);
	assertThat(productRepository.findAll().size()).isEqualTo(2);
	assertThat(productRepository.findAll().get(0).getType()).isEqualTo("jeans");
    }
    @Test
    public void getListOfProductsSortedByPriceASC() {
	Product product = new Product();
	Product product2 = new Product();
	product.setName("name");
	product.setPrice(337);
	product.setType("jeans");
	product.setCode(111);
	product.setDescription("notBad");
	product.setSize("xxl");
	product.setInStock(true);
	product2.setName("name1");
	product2.setPrice(3371);
	product2.setType("jeans");
	product2.setCode(222);
	product2.setDescription("notBad");
	product.setSize("xxl");
	product2.setInStock(true);
	productRepository.save(product);
	productRepository.save(product2);
	assertThat(productRepository.findAll().size()).isEqualTo(2);
	assertThat(productRepository.getListOfProductsSortedByPrice("jeans").size()).isEqualTo(2);
	assertThat(productRepository.getListOfProductsSortedByPrice("jeans").get(0).getPrice()).isEqualTo(337);
    }
    @Test
    public void getListOfProductsSortedByPriceDesc() {
	Product product = new Product();
	Product product2 = new Product();
	product.setName("name");
	product.setPrice(337);
	product.setType("jeans");
	product.setCode(111);
	product.setDescription("notBad");
	product.setSize("xxl");
	product.setInStock(true);
	product2.setName("name1");
	product2.setPrice(3371);
	product2.setType("jeans");
	product2.setCode(222);
	product2.setDescription("notBad");
	product.setSize("xxl");
	product2.setInStock(true);
	productRepository.save(product);
	productRepository.save(product2);
	assertThat(productRepository.findAll().size()).isEqualTo(2);
	assertThat(productRepository.getListOfProductsSortedByPriceDESC("jeans").size()).isEqualTo(2);
	assertThat(productRepository.getListOfProductsSortedByPriceDESC("jeans").get(0).getPrice()).isEqualTo(3371);
    }
}
