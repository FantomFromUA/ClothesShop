package ua.tony.mapper;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import ua.tony.entity.Product;
import ua.tony.dto.ProductDto;

@Component
public class ProductMapper {

	public Product convertToEntity(ProductDto productDto) {

		Product product = new Product();
		product.setId(productDto.getId());
		product.setCode(productDto.getCode());
		product.setDescription(productDto.getDescription());
		product.setInStock(productDto.getInStock());
		product.setName(productDto.getName());
		product.setPrice(productDto.getPrice());
		product.setSize(productDto.getSize());
		product.setType(productDto.getType());

		return product;
	}

	public ProductDto convertToDto(Product product) {

		ProductDto productDto = new ProductDto();
		productDto.setId(product.getId());
		productDto.setCode(product.getCode());
		productDto.setDescription(product.getDescription());
		productDto.setInStock(product.getInStock());
		productDto.setName(product.getName());
		productDto.setPrice(product.getPrice());
		productDto.setSize(product.getSize());
		productDto.setType(product.getType());

		return productDto;
	}

}
