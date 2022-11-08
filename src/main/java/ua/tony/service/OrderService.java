package ua.tony.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import ua.tony.dto.OrderDto;
import ua.tony.entity.Order;
import ua.tony.exeption.OrderNotDeletedException;
import ua.tony.exeption.OrderNotFoundException;
import ua.tony.mapper.OrderMapper;
import ua.tony.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderRepository orderRepo;

	/**
	 * Метод, який зберігає заказ в БД
	 * 
	 * @param orderDto - заказ
	 * @return заказ
	 */
	public OrderDto save(OrderDto orderDto) {
		Order order = orderMapper.convertToEntity(orderDto);
		return orderMapper.convertToDto(orderRepo.save(order));
	}

	/**
	 * Метод, який оновлює інформацію про заказ в БД
	 * 
	 * @param orderDto - заказ
	 * @return заказ
	 */
	public OrderDto update(OrderDto orderDto) {

		Order order = orderRepo.findById(orderDto.getId()).get();
		if (orderDto.getCompleted()) {

			for (int i = 0; i < order.getOrderItems().size(); i++) {

				order.getOrderItems().get(i).getProduct().setInStock(false);
			}
		}
		return orderMapper.convertToDto(orderRepo.save(order));
	}

	/**
	 * Метод, який знаходить та повертає заказ за заданим id
	 * 
	 * @param id - id заказу
	 * @return заказ
	 */
	public OrderDto findById(Integer id) throws OrderNotFoundException {

		if (orderRepo.findById(id).isPresent())
			return orderMapper.convertToDto(orderRepo.findById(id).get());
		else
			throw new OrderNotFoundException("order is not found by this id :" + id.toString());
	}

	/**
	 * Метод, який знаходить закази за заданою датою створення заказу
	 * 
	 * @param orderDate - дата створення заказу
	 * @return список заказів
	 */
	public List<OrderDto> findByOrderDate(LocalDate orderDate) throws OrderNotFoundException {

		if (orderRepo.findByOrderDate(orderDate).size() > 0) {
			List<OrderDto> orders = orderRepo.findByOrderDate(orderDate).stream().map(x -> orderMapper.convertToDto(x))
					.toList();
			return orders;
		} else
			throw new OrderNotFoundException("orders are not found by this order date :" + orderDate.toString());
	}

	/**
	 * Метод, який знаходить закази за заданою датою доставки заказу
	 * 
	 * @param deliveryDate - дата доставки заказу
	 * @return список заказів
	 */
	public List<OrderDto> findByDeliveryDate(LocalDate deliveryDate) throws OrderNotFoundException {
		if (orderRepo.findByDeliveryDate(deliveryDate).size() > 0) {
			List<OrderDto> orders = orderRepo.findByDeliveryDate(deliveryDate).stream()
					.map(x -> orderMapper.convertToDto(x)).toList();
			return orders;
		} else
			throw new OrderNotFoundException("order are not found by this delivery date :" + deliveryDate.toString());

	}

	/**
	 * Метод, який зберігає заказ в БД
	 * 
	 * @return список заказів
	 */
	public List<OrderDto> findAll() throws OrderNotFoundException {

		if (orderRepo.findAll().size() > 0) {
			List<OrderDto> orders = orderRepo.findAll().stream().map(x -> orderMapper.convertToDto(x)).toList();
			return orders;
		} else
			throw new OrderNotFoundException("orders are not found in DB");
	}

	/**
	 * Метод, який видаляє всі дані з таблиці orders в БД
	 */
	public void deleteAll() {

		orderRepo.deleteAll();
	}

	/**
	 * Метод, який видаляє заказ за заданим id
	 * 
	 * @param id - id заказу
	 */
	public void deleteById(Integer id) throws OrderNotDeletedException {

		try {
			orderRepo.deleteById(id);
		} catch (EmptyResultDataAccessException exc) {
			throw new OrderNotFoundException("order is not deleted by this id :" + id.toString());
		}
	}

	/**
	 * Метод, який повертає список заказів, який відноситься до заданого користувача
	 * 
	 * @param userId - id користувача
	 * @return список заказів
	 */
	public List<OrderDto> getOrdersWhichRelatedToUser(Integer userId) throws OrderNotFoundException {

		if (orderRepo.getOrdersThatRelatedToUser(userId).size() > 0) {
			List<OrderDto> orders = orderRepo.getOrdersThatRelatedToUser(userId).stream()
					.map(x -> orderMapper.convertToDto(x)).toList();
			return orders;
		} else
			throw new OrderNotFoundException("orders are not found by this user id :" + userId.toString());

	}
}