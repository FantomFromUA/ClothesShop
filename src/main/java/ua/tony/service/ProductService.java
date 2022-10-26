package ua.tony.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.tony.dto.ProductDto;
import ua.tony.entity.Product;
import ua.tony.mapper.ProductMapper;
import ua.tony.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductMapper productMapper;
	@Autowired
	ProductRepository productRepo;

	/**
	 * Метод, який зберігає продукт в БД
	 * 
	 * @param productDto - продукт
	 * @return продукт
	 */
	public ProductDto save(ProductDto productDto) {

		Product product = productMapper.convertToEntity(productDto);
		return productMapper.convertToDto(productRepo.save(product));
	}

	/**
	 * Метод, який оновлює інформацію про продукт в БД
	 * 
	 * @param productDto - продукт
	 * @return продукт
	 */
	public ProductDto update(ProductDto productDto) {

		Product product = productMapper.convertToEntity(productDto);
		return productMapper.convertToDto(productRepo.save(product));
	}

	/**
	 * Метод, який знаходить та повертає продукт за заданим id
	 * 
	 * @param id - id продукта
	 * @return продукт
	 */
	public ProductDto findById(Integer id) {

		return productMapper.convertToDto(productRepo.findById(id).get());
	}

	/**
	 * Метод, який знаходить та повертає список продуктів за заданим типом
	 * 
	 * @param type - тип продукта
	 * @return список продуктів
	 */
	public List<ProductDto> findByType(String type) {

		List<ProductDto> productDtoes = productRepo.findByType(type).stream().map(x -> productMapper.convertToDto(x))
				.toList();
		return productDtoes;
	}

	/**
	 * Метод, який знаходить та повертає список продуктів за заданою назвою
	 * 
	 * @param type - назва продукта
	 * @return список продуктів
	 */
	public List<ProductDto> findByName(String name) {

		List<ProductDto> productDtoes = productRepo.findByName(name).stream().map(x -> productMapper.convertToDto(x))
				.toList();
		return productDtoes;
	}

	/**
	 * Метод, який повертає всі дані з таблиці products в БД
	 * 
	 * @return список продуктів
	 */
	public List<ProductDto> findAll() {

		List<ProductDto> productDtoes = productRepo.findAll().stream().map(x -> productMapper.convertToDto(x)).toList();
		return productDtoes;
	}

	/**
	 * Метод, який видаляє всі дані з таблиці products в БД
	 */
	public void deleteAll() {

		productRepo.deleteAll();
	}

	/**
	 * Метод, який видаляє продукт за заданим id
	 * 
	 * @param id - id продукта
	 */
	public void deleteById(Integer id) {

		productRepo.deleteById(id);
	}

	// ???
	public List<ProductDto> getProductsThatBoughtUser(Integer userId) {

		List<ProductDto> products = productRepo.getProductsThatBoughtUser(userId).stream()
				.map(x -> productMapper.convertToDto(x)).toList();
		return products;
	}

	/**
	 * Метод, який повертає список продуктів заданого типу посортованих за
	 * зростанням ціни
	 * 
	 * @param type - тип продукта
	 * @return список продуктів
	 */
	public List<ProductDto> getListOfProductsSortedByPrice(String type) {

		List<ProductDto> products = productRepo.getListOfProductsSortedByPrice(type).stream()
				.map(x -> productMapper.convertToDto(x)).toList();
		return products;
	}

	/**
	 * Метод, який повертає список продуктів заданого типу посортованих за спаданням
	 * ціни
	 * 
	 * @param type - тип продукта
	 * @return список продуктів
	 */
	public List<ProductDto> getListOfProductsSortedByPriceDESC(String type) {

		List<ProductDto> products = productRepo.getListOfProductsSortedByPriceDESC(type).stream()
				.map(x -> productMapper.convertToDto(x)).toList();
		return products;
	}
}
