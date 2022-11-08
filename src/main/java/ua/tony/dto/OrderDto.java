package ua.tony.dto;

import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class OrderDto {

	private int id;

	private double totalPrice;

	private LocalDate orderDate;

	private LocalDate deliveryDate;

	private boolean isCompleted;

	@NotNull(message = "should not be empty")
	private UserDto userDto;

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

	@Override
	public String toString() {
		return "Order [id=" + id + ", totalPrice=" + totalPrice + ", orderDate=" + orderDate + ", deliveryDate="
				+ deliveryDate + ", isCompleted=" + isCompleted + ", user=" + userDto + "]";
	}
}