package ua.tony.dto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ua.tony.entity.OrderItem;
import ua.tony.entity.User;

public class OrderDto {

    private int id;

    private double totalPrice;

    private LocalDate orderDate;

    private LocalDate deliveryDate;

    private boolean isCompleted;

    private UserDto userDto;

    private List<OrderItemDto> orderItemsDto;

    public OrderDto() {

    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public double getTotalPrice() {
	return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
	this.totalPrice = totalPrice;
    }

    public LocalDate getOrderDate() {
	return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
	this.orderDate = orderDate;
    }

    public LocalDate getDeliveryDate() {
	return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
	this.deliveryDate = deliveryDate;
    }

    public boolean getCompleted() {
	return isCompleted;
    }

    public void setCompleted(boolean isCompleted) {
	this.isCompleted = isCompleted;
    }

    public UserDto getUserDto() {
	return userDto;
    }

    public void setUserDto(UserDto userDto) {
	this.userDto = userDto;
    }

    public List<OrderItemDto> getOrderItemsDto() {
	return orderItemsDto;
    }

    public void setOrderItemsDto(List<OrderItemDto> orderItemsDto) {
	this.orderItemsDto = orderItemsDto;
    }

    @Override
    public String toString() {
	return "Order [id=" + id + ", totalPrice=" + totalPrice + ", orderDate=" + orderDate + ", deliveryDate="
		+ deliveryDate + ", isCompleted=" + isCompleted + ", user=" + userDto + ", orderItems=" + orderItemsDto
		+ "]";
    }
}