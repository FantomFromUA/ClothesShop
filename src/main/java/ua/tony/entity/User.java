package ua.tony.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "surname")
	private String surname;

	@Column(name = "login")
	private String login;

	@Column(name = "password")
	private String password;

	@Column(name = "coins")
	private double coins;

	@Column(name = "admin_access")
	private boolean adminAccess;
	
	@Column(name="token")
	private String token;
	
	@Column(name="is_avaliable")
	private boolean isAvailable;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<Order> orders;

	public User() {
	}

	public User(String name, String surname, String login, String password, double coins, boolean adminAccess,
			String token, boolean isAvailable) {
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = password;
		this.coins = coins;
		this.adminAccess = adminAccess;
		this.token = token;
		this.isAvailable = isAvailable;
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getCoins() {
		return coins;
	}

	public void setCoins(double coins) {
		this.coins = coins;
	}

	public boolean getAdminAccess() {
		return adminAccess;
	}

	public void setAdminAccess(boolean adminAccess) {
		this.adminAccess = adminAccess;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}	

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", login=" + login + ", password="
				+ password + ", coins=" + coins + ", adminAccess=" + adminAccess + ", token=" + token + ", isAvailable="
				+ isAvailable + ", orders=" + orders + "]";
	}
	
}
