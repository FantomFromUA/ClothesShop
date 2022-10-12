package ua.tony.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import ua.tony.entity.Order;
import ua.tony.entity.Product;

public class OrderItemDto {

    private int id;

    private OrderDto orderDto;

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
	orderDto.setTotalPrice(orderDto.getTotalPrice() + this.productDto.getPrice());
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
