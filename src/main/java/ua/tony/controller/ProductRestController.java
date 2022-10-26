package ua.tony.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import ua.tony.dto.ProductDto;
import ua.tony.service.ProductService;

@RestController
@RequestMapping("/")
@Tag(name = "Product", description = "this Controller helps client to interact with Products ")
public class ProductRestController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "products", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    @Operation(summary = "1.Helps to get product by its id " + "2.Helps to get products by their name "
	    + "3.Helps to get products by their type " + "4.Helps to get all products from DB")
    public ResponseEntity<List<ProductDto>> getProducsByIdOrByTypeOrByNameOrGetAllProducts(
	    @RequestParam(value = "product_name", required = false) String product_name,
	    @RequestParam(value = "product_type", required = false) String product_type,
	    @RequestParam(value = "product_id", required = false) Integer product_id) {
	if (product_name == null && product_type == null && product_id != null) {

	    List<ProductDto> product = new ArrayList<>();
	    product.add(productService.findById(product_id));
	    return ResponseEntity.ok(product);
	}
	if (product_name == null && product_type != null && product_id == null) {
	    var temp = productService.findByType(product_type);
	    if (temp.size() == 0) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    }
	    return ResponseEntity.ok(temp);
	}
	if (product_name != null && product_type == null && product_id == null)
	    return ResponseEntity.ok(productService.findByName(product_name));
	if (product_name == null && product_type == null && product_id == null)
	    return ResponseEntity.ok(productService.findAll());
	else
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @RequestMapping(value = "ursers/products", method = RequestMethod.GET, produces = {
	    MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    @Operation(summary = "Helps to get all products that user has bought")
    public ResponseEntity<List<ProductDto>> getProductsThatBoughtUser(
	    @RequestParam(value = "user_id", required = false) Integer userId) {
	if (userId != null)
	    return ResponseEntity.ok(productService.getProductsThatBoughtUser(userId));
	else
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @RequestMapping(value = "hoodies", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    @Operation(summary = "1.Helps to get all hoodies "
	    + "2.Helps to get all hoodies which ordered by teir price. If you want to ascent price put this parametr : \"asc\" "
	    + "3.Helps to get all hoodies which ordered by teir price. If you want to decline price put this parametr : \"desc\" ")
    @ResponseBody
    public ResponseEntity<List<ProductDto>> getHoodiesByPriceByGrowthOrByDeclByType(
	    @RequestParam(value = "sort", required = false) String sortOption) {

	if (sortOption == null)
	    return ResponseEntity.ok(productService.findByType("hoodie"));
	if (sortOption.equals("asc")) {
	    return ResponseEntity.ok(productService.getProductsOrderedByPriceByGrowthByType("hoodie"));
	}
	if (sortOption.equals("desc"))
	    return ResponseEntity.ok(productService.getProductsOrderedByPriceByDeclineByType("hoodie"));
	else
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @RequestMapping(value = "jeans", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    @Operation(summary = "1.Helps to get all jeans "
	    + "2.Helps to get all jeans which ordered by teir price. If you want to ascent price put this parametr : \"asc\" "
	    + "3.Helps to get all jeans which ordered by teir price. If you want to decline price put this parametr : \"desc\" ")
    @ResponseBody
    public ResponseEntity<List<ProductDto>> getJeansByPriceByGrowthOrByDeclByType(
	    @RequestParam(value = "sort", required = false) String sortOption) {

	if (sortOption == null)
	    return ResponseEntity.ok(productService.findByType("jeans"));
	if (sortOption.equals("asc")) {
	    return ResponseEntity.ok(productService.getProductsOrderedByPriceByGrowthByType("jeans"));
	}
	if (sortOption.equals("desc"))
	    return ResponseEntity.ok(productService.getProductsOrderedByPriceByDeclineByType("jeans"));
	else
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
    @RequestMapping(value = "shorts", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    @Operation(summary = "1.Helps to get all shorts "
	    + "2.Helps to get all shorts which ordered by teir price. If you want to ascent price put this parametr : \"asc\" "
	    + "3.Helps to get all shorts which ordered by teir price. If you want to decline price put this parametr : \"desc\" ")
    @ResponseBody
    public ResponseEntity<List<ProductDto>> getShortsByPriceByGrowthOrByDeclByType(
	    @RequestParam(value = "sort", required = false) String sortOption) {
	
	if (sortOption == null)
	    return ResponseEntity.ok(productService.findByType("shorts"));
	if (sortOption.equals("asc")) {
	    return ResponseEntity.ok(productService.getProductsOrderedByPriceByGrowthByType("shorts"));
	}
	if (sortOption.equals("desc"))
	    return ResponseEntity.ok(productService.getProductsOrderedByPriceByDeclineByType("shorts"));
	else
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @RequestMapping(value = "products", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    @Operation(summary = "Helps to create new product")
    public ResponseEntity<ProductDto> createProduct(@RequestBody @Valid ProductDto productDto) {

	return new ResponseEntity<>(productService.save(productDto), HttpStatus.CREATED);
    }

    @RequestMapping(value = "products", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    @Operation(summary = "Helps to update already exists product")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody @Valid ProductDto productDto) {

	return new ResponseEntity<>(productService.update(productDto), HttpStatus.CREATED);
    }

    @RequestMapping(value = "products", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    @Operation(summary = "1.Helps to delete product by its id " + "2.Helps to delete all products ")
    public ResponseEntity<HttpStatus> deleteProductByIdOrDeleteAllProductsFromDb(
	    @RequestParam(value = "product_id", required = false) Integer product_id) {
	if (product_id == null) {
	    productService.deleteAll();
	} else {
	    productService.deleteById(product_id);
	}
	return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

}
