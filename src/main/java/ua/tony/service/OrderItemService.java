package ua.tony.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import ua.tony.dto.OrderItemDto;
import ua.tony.entity.Order;
import ua.tony.entity.OrderItem;
import ua.tony.exeption.OrderItemNotDeletedException;
import ua.tony.exeption.OrderItemNotFoundException;
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
	 * 
	 * @param orderItemDto - елемент заказу
	 * @return елемент заказу
	 * 
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
	public OrderItemDto findById(Integer id) throws OrderItemNotFoundException {

		if (orderItemRepo.findById(id).isPresent())
			return orderItemMapper.convertToDto(orderItemRepo.findById(id).get());
		else
			throw new OrderItemNotFoundException("orderitem is not found by this id :" + id.toString());
	}

	/**
	 * Метод, який знаходить та повертає елемент заказу за заданим id продукта
	 * 
	 * @param productId - id продукта
	 * @return елемент заказу
	 */
	public OrderItemDto findByProductId(Integer productId) throws OrderItemNotFoundException {
		if (orderItemRepo.findByProductId(productId) != null)

			return orderItemMapper.convertToDto(orderItemRepo.findByProductId(productId));
		else
			throw new OrderItemNotFoundException("orderitem is not found by this product id :" + productId.toString());
	}

	/**
	 * Метод, який знаходить список елементів заказу за заданим id заказу
	 * 
	 * @param orderId - id заказу
	 * @return список елементів заказу
	 */
	public List<OrderItemDto> findByOrderId(Integer orderId) throws OrderItemNotFoundException {
		if (orderItemRepo.findByOrderId(orderId).size() > 0)
			return orderItemRepo.findByOrderId(orderId).stream()
					.map(orderItem -> orderItemMapper.convertToDto(orderItem)).toList();
		else
			throw new OrderItemNotFoundException("orderitems are not found by this id :" + orderId.toString());

	}

	/**
	 * Метод, який повертає всі дані з таблиці order_items в БД
	 * 
	 * @return список елементів заказу
	 */
	public List<OrderItemDto> findAll() throws OrderItemNotFoundException {
		if (orderItemRepo.findAll().size() > 0) {
			List<OrderItemDto> orderItems = orderItemRepo.findAll().stream().map(x -> orderItemMapper.convertToDto(x))
					.toList();
			return orderItems;
		} else
			throw new OrderItemNotFoundException("orderitems are not found in DB");
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
	public void deleteById(Integer id) throws OrderItemNotDeletedException {
		try {
			orderItemRepo.deleteById(id);
		} catch (EmptyResultDataAccessException exc) {
			throw new OrderItemNotDeletedException("orderitem is not deleted by this id :" + id.toString());
		}
	}

}
