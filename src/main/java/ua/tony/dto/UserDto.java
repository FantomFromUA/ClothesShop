package ua.tony.dto;

import java.util.List;


public class UserDto {

    private int id;

    private String name;

    private String surname;

    private String login;

    private String password;

    private double coins;

    private boolean adminAccess;

    private List<OrderDto> ordersDto;

    public UserDto() {
    }

    public UserDto(String name, String surname, String login, String password, double coins, boolean adminAccess) {
	this.name = name;
	this.surname = surname;
	this.login = login;
	this.password = password;
	this.coins = coins;
	this.adminAccess = adminAccess;
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



    public List<OrderDto> getOrdersDto() {
        return ordersDto;
    }

    public void setOrdersDto(List<OrderDto> ordersDto) {
        this.ordersDto = ordersDto;
    }

    @Override
    public String toString() {
	return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", login=" + login + ", password="
		+ password + ", coins=" + coins + ", adminAccess=" + adminAccess + "]";
    }
}
