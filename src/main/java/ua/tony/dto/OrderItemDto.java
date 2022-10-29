package ua.tony.dto;

import javax.validation.constraints.NotNull;

public class OrderItemDto {

	private int id;

	@NotNull(message = "should not be empty")
	private OrderDto orderDto;

	@NotNull(message = "should not be empty")
	private ProductDto productDto;

	public OrderItemDto() {
	}

	public OrderItemDto(OrderDto orderDto, ProductDto productDto) {
		this.orderDto = orderDto;
		this.productDto = productDto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public OrderDto getOrderDto() {
		return orderDto;
	}

	public void setOrderDto(OrderDto orderDto) {
		this.orderDto = orderDto;
	}

	public ProductDto getProductDto() {
		return productDto;
	}

	public void setProductDto(ProductDto productDto) {
		this.productDto = productDto;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", order=" + orderDto + ", product=" + productDto + "]";
	}
}
