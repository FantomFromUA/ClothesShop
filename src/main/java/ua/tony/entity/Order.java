package ua.tony.entity;

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

@Entity
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="total_price")
	private double totalPrice;
	
	@Column(name="order_date")
	private LocalDate orderDate;
	
	@Column(name="delivery_date")
	private LocalDate deliveryDate;
	
	@Column(name="completed")
	private boolean isCompleted;
	
	@ManyToOne(fetch=FetchType.EAGER,
			   cascade={CascadeType.DETACH, CascadeType.MERGE, 
					    CascadeType.REFRESH})
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToMany(mappedBy="order",
			   fetch=FetchType.EAGER,
			   cascade=CascadeType.ALL)
	private List<OrderItem> orderItems;
	
	public Order() {
		
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

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	

	@Override
	public String toString() {
		return "Order [id=" + id + ", totalPrice=" + totalPrice + ", orderDate=" + orderDate + ", deliveryDate="
				+ deliveryDate + ", isCompleted=" + isCompleted + ", user=" + user + ", orderItems=" + orderItems + "]";
	}
}
