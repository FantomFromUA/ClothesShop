package ua.tony.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.tony.dto.OrderDto;
import ua.tony.entity.Order;
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
	public OrderDto findById(Integer id) {

		return orderMapper.convertToDto(orderRepo.findById(id).get());
	}

	/**
	 * Метод, який знаходить закази за заданою датою створення заказу
	 * 
	 * @param orderDate - дата створення заказу
	 * @return список заказів
	 */
	public List<OrderDto> findByOrderDate(LocalDate orderDate) {

		List<OrderDto> orders = orderRepo.findByOrderDate(orderDate).stream().map(x -> orderMapper.convertToDto(x))
				.toList();
		return orders;
	}

	/**
	 * Метод, який знаходить закази за заданою датою доставки заказу
	 * 
	 * @param deliveryDate - дата доставки заказу
	 * @return список заказів
	 */
	public List<OrderDto> findByDeliveryDate(LocalDate orderDate) {

		List<OrderDto> orders = orderRepo.findByDeliveryDate(orderDate).stream().map(x -> orderMapper.convertToDto(x))
				.toList();
		return orders;
	}

	/**
	 * Метод, який зберігає заказ в БД
	 * 
	 * @return список заказів
	 */
	public List<OrderDto> findAll() {

		List<OrderDto> orders = orderRepo.findAll().stream().map(x -> orderMapper.convertToDto(x)).toList();
		return orders;
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
	public void deleteById(Integer id) {

		orderRepo.deleteById(id);
	}

	/**
	 * Метод, який повертає список заказів, який відноситься до заданого користувача
	 * 
	 * @param userId - id користувача
	 * @return список заказів
	 */
	public List<OrderDto> getOrdersThatRelatedToUser(Integer userId) {

		List<OrderDto> orders = orderRepo.getOrdersThatRelatedToUser(userId).stream()
				.map(x -> orderMapper.convertToDto(x)).toList();
		return orders;
	}
}