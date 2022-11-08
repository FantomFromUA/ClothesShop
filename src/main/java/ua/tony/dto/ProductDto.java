package ua.tony.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ProductDto {

	private int id;

	@NotEmpty(message = "should not be empty")
	@Size(min = 2, message = "product name should have at least 2 characters")
	private String name;

	@NotEmpty(message = "should not be empty")
	@Size(min = 2, message = "product type should have at least 2 characters")
	private String type;

	private int code;

	@NotEmpty(message = "should not be empty")
	@Size(min = 1, max = 4, message = "product size should be in range 1 to 4")
	private String size;

	@DecimalMin("0.1")
	private double price;

	private String description;

	private boolean inStock;

	public ProductDto() {
	}

	public ProductDto(String name, String type, int code, String size, double price, String description) {
		this.name = name;
		this.type = type;
		this.code = code;
		this.size = size;
		this.price = price;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", type=" + type + ", code=" + code + ", size=" + size
				+ ", price=" + price + ", description=" + description + ", inStock=" + inStock + "]";
	}
}
