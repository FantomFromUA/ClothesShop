package ua.tony.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import ua.tony.dto.ProductDto;
import ua.tony.entity.Product;
import ua.tony.exeption.ProductNotDeletedException;
import ua.tony.exeption.ProductNotFoundException;
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
	public ProductDto findById(Integer id) throws ProductNotFoundException {

		if (productRepo.findById(id).isPresent())
			return productMapper.convertToDto(productRepo.findById(id).get());
		else
			throw new ProductNotFoundException("product is not found by this id :" + id.toString());
	}

	/**
	 * Метод, який знаходить та повертає список продуктів за заданим типом
	 * 
	 * @param type - тип продукта
	 * @return список продуктів
	 */
	public List<ProductDto> findByType(String type) throws ProductNotFoundException {
		if (productRepo.findByType(type).size() > 0) {
			List<ProductDto> productDtoes = productRepo.findByType(type).stream()
					.map(x -> productMapper.convertToDto(x)).toList();
			return productDtoes;
		} else
			throw new ProductNotFoundException("producs are not found by this type :" + type);
	}

	/**
	 * Метод, який знаходить та повертає список продуктів за заданою назвою
	 * 
	 * @param type - назва продукта
	 * @return список продуктів
	 */
	public List<ProductDto> findByName(String name) throws ProductNotFoundException {

		if (productRepo.findByName(name).size() > 0) {
			List<ProductDto> productDtoes = productRepo.findByName(name).stream()
					.map(x -> productMapper.convertToDto(x)).toList();
			return productDtoes;
		} else
			throw new ProductNotFoundException("producs are not found by this name :" + name);
	}

	/**
	 * Метод, який повертає всі дані з таблиці products в БД
	 * 
	 * @return список продуктів
	 */
	public List<ProductDto> findAll() throws ProductNotFoundException {
		if (productRepo.findAll().size() > 0) {

			List<ProductDto> productDtoes = productRepo.findAll().stream().map(x -> productMapper.convertToDto(x))
					.toList();
			return productDtoes;
		} else
			throw new ProductNotFoundException("products are not found from DB");
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
	public void deleteById(Integer id) throws ProductNotDeletedException {
		try {
			productRepo.deleteById(id);
		} catch (EmptyResultDataAccessException exc) {
			throw new ProductNotDeletedException("product is not deleted by this id :" + id.toString());
		}
	}

	// ???
	public List<ProductDto> getProductsThatBoughtUser(Integer userId) throws ProductNotFoundException {

		if (productRepo.getProductsThatBoughtUser(userId).size() > 0) {
			List<ProductDto> products = productRepo.getProductsThatBoughtUser(userId).stream()
					.map(x -> productMapper.convertToDto(x)).toList();
			return products;
		} else
			throw new ProductNotFoundException(
					"there are no products which user bought, user id =" + userId.toString());
	}

	/**
	 * Метод, який повертає список продуктів заданого типу посортованих за
	 * зростанням ціни
	 * 
	 * @param type - тип продукта
	 * @return список продуктів
	 */
	public List<ProductDto> getListOfProductsSortedByPrice(String type) throws ProductNotFoundException {

		if (productRepo.getListOfProductsSortedByPrice(type).size() > 0) {
			List<ProductDto> products = productRepo.getListOfProductsSortedByPrice(type).stream()
					.map(x -> productMapper.convertToDto(x)).toList();
			return products;
		} else
			throw new ProductNotFoundException("products are not sorted and not found by this type :" + type);
	}

	/**
	 * Метод, який повертає список продуктів заданого типу посортованих за спаданням
	 * ціни
	 * 
	 * @param type - тип продукта
	 * @return список продуктів
	 */
	public List<ProductDto> getListOfProductsSortedByPriceDESC(String type) throws ProductNotFoundException {

		if (productRepo.getListOfProductsSortedByPriceDESC(type).size() > 0) {
			List<ProductDto> products = productRepo.getListOfProductsSortedByPriceDESC(type).stream()
					.map(x -> productMapper.convertToDto(x)).toList();
			return products;
		} else
			throw new ProductNotFoundException("products are not sorted and not found by this type :" + type);
	}
}
