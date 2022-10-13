package ua.tony.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.tony.dto.OrderDto;
import ua.tony.dto.OrderItemDto;
import ua.tony.entity.OrderItem;
import ua.tony.mapper.OrderItemMapper;
import ua.tony.repository.OrderItemRepository;

@Service
public class OrderItemService {

	@Autowired
	private OrderItemMapper orderItemMapper;
	@Autowired
	private OrderItemRepository orderItemRepo;

	public OrderItemDto save(OrderItemDto orderItemDto) {

		OrderItem orderItem = orderItemMapper.convertToEntity(orderItemDto);
		return orderItemMapper.convertToDto(orderItemRepo.save(orderItem));
	}

	public OrderItemDto update(OrderItemDto orderItemDto) {

		OrderItem orderItem = orderItemMapper.convertToEntity(orderItemDto);
		return orderItemMapper.convertToDto(orderItemRepo.save(orderItem));
	}

	public OrderItemDto findById(Integer id) {

		return orderItemMapper.convertToDto(orderItemRepo.findById(id).get());
	}

	public OrderItemDto findByProductId(Integer productId) {

		return orderItemMapper.convertToDto(orderItemRepo.findByProductId(productId));
	}

	public OrderItemDto findByOrderId(Integer orderId) {

		return orderItemMapper.convertToDto(orderItemRepo.findByOrderId(orderId));
	}

	public List<OrderItemDto> findAll() {

		List<OrderItemDto> orderItems = orderItemRepo.findAll().stream().map(x -> orderItemMapper.convertToDto(x))
				.toList();
		return orderItems;
	}

	public void deleteAll() {

		orderItemRepo.deleteAll();
	}

	public void deleteById(Integer id) {

		orderItemRepo.deleteById(id);
	}

	public List<OrderItemDto> getOrderItemsThatRelatedToOrder(Integer orderId) {
		List<OrderItemDto> orderItems = orderItemRepo.getOrderItemsThatRelatedToOrder(orderId).stream()
				.map(x -> orderItemMapper.convertToDto(x)).toList();
		return orderItems;
	}
}
