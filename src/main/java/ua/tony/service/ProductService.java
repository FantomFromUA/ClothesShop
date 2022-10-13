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

    public ProductDto save(ProductDto productDto) {
	Product product = productMapper.convertToEntity(productDto);
	return productMapper.convertToDto(productRepo.save(product));
    }

    public ProductDto update(ProductDto productDto) {
	Product product = productMapper.convertToEntity(productDto);
	return productMapper.convertToDto(productRepo.save(product));
    }

    public ProductDto findById(Integer id) {

	return productMapper.convertToDto(productRepo.findById(id).get());
    }

    public List<ProductDto> findByType(String type) {

	List<ProductDto> productDtoes = productRepo.findByType(type).stream().map(x -> productMapper.convertToDto(x))
		.toList();
	return productDtoes;
    }

    public List<ProductDto> findByName(String name) {

	List<ProductDto> productDtoes = productRepo.findByName(name).stream().map(x -> productMapper.convertToDto(x))
		.toList();
	return productDtoes;
    }

    public void deleteAll() {

	productRepo.deleteAll();
    }

    public void deleteById(Integer id) {

	productRepo.deleteById(id);
    }

}
