package ua.tony.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.tony.dto.OrderItemDto;
import ua.tony.entity.Order;
import ua.tony.entity.OrderItem;
import ua.tony.mapper.OrderItemMapper;
import ua.tony.repository.OrderItemRepository;
import ua.tony.repository.OrderRepository;

@Service
public class OrderItemService {

	@Autowired
	private OrderItemMapper orderItemMapper;
	@Autowired
	private OrderItemRepository orderItemRepo;
	@Autowired
	private OrderRepository orderRepo;

	/**
	 * Метод, який зберігає елемент заказу в БД
	 * 
	 * @param orderItemDto - елемент заказу
	 * @return елемент заказу
	 */
	public OrderItemDto save(OrderItemDto orderItemDto) {

		OrderItem orderItem = orderItemMapper.convertToEntity(orderItemDto);
		Order order = orderRepo.findById(orderItemDto.getOrderDto().getId()).get();
		order.setTotalPrice(order.getTotalPrice() + orderItemDto.getProductDto().getPrice());
		orderRepo.save(order);
		return orderItemMapper.convertToDto(orderItemRepo.save(orderItem));
	}

	/**
	 * 
	 * Метод, який оновлює інформацію про елемент заказу в БД
	 * 
	 * @param orderItemDto - елемент заказу
	 * @return елемент заказу
	 */
	public OrderItemDto update(OrderItemDto orderItemDto) {

		OrderItem orderItem = orderItemMapper.convertToEntity(orderItemDto);
		return orderItemMapper.convertToDto(orderItemRepo.save(orderItem));
	}

	/**
	 * Метод, який знаходить та повертає елемент заказу за заданим id
	 * 
	 * @param id - id елемента заказу
	 * @return елемент заказу
	 */
	public OrderItemDto findById(Integer id) {

		return orderItemMapper.convertToDto(orderItemRepo.findById(id).get());
	}

	/**
	 * Метод, який знаходить та повертає елемент заказу за заданим id продукта
	 * 
	 * @param productId - id продукта
	 * @return елемент заказу
	 */
	public OrderItemDto findByProductId(Integer productId) {

		return orderItemMapper.convertToDto(orderItemRepo.findByProductId(productId));
	}

	/**
	 * Метод, який знаходить список елементів заказу за заданим id заказу
	 * 
	 * @param orderId - id заказу
	 * @return список елементів заказу
	 */
	public List<OrderItemDto> findByOrderId(Integer orderId) {
		return orderItemRepo.findByOrderId(orderId).stream().map(orderItem -> orderItemMapper.convertToDto(orderItem))
				.toList();

	}

	/**
	 * Метод, який повертає всі дані з таблиці order_items в БД
	 * 
	 * @return список елементів заказу
	 */
	public List<OrderItemDto> findAll() {

		List<OrderItemDto> orderItems = orderItemRepo.findAll().stream().map(x -> orderItemMapper.convertToDto(x))
				.toList();
		return orderItems;
	}

	/**
	 * Метод, який видаляє всі дані з таблиці order_items в БД
	 */
	public void deleteAll() {

		orderItemRepo.deleteAll();
	}

	/**
	 * Метод, який видаляє елемент заказу за заданим id
	 * 
	 * @param id - id елемента заказу
	 */
	public void deleteById(Integer id) {

		orderItemRepo.deleteById(id);
	}

	/**
	 * Метод, який повертає список елементів заказу, який відноситься до заданого
	 * заказу
	 * 
	 * @param orderId - id заказу
	 * @return список елементів заказу
	 */
	public List<OrderItemDto> getOrderItemsThatRelatedToOrder(Integer orderId) {
		List<OrderItemDto> orderItems = orderItemRepo.getOrderItemsThatRelatedToOrder(orderId).stream()
				.map(x -> orderItemMapper.convertToDto(x)).toList();
		return orderItems;
	}
}
