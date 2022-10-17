package ua.tony.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import ua.tony.dto.OrderDto;
import ua.tony.entity.Order;
import ua.tony.entity.OrderItem;

@Component
public class OrderMapper {

	private UserMapper userMapper;
	private OrderItemMapper orderItemMapper;

	@Autowired
	public OrderMapper(@Lazy UserMapper userMapper, @Lazy OrderItemMapper orderItemMapper) {
		this.userMapper = userMapper;
		this.orderItemMapper = orderItemMapper;

	}

	public Order convertToEntity(OrderDto orderDto) {

		Order order = new Order();
		order.setId(orderDto.getId());
		order.setCompleted(orderDto.getCompleted());
		order.setDeliveryDate(orderDto.getDeliveryDate());
		order.setOrderDate(orderDto.getOrderDate());
		order.setTotalPrice(orderDto.getTotalPrice());
		if(orderDto.getUserDto() != null) {
			order.setUser(userMapper.convertToEntity(orderDto.getUserDto()));
		}

		return order;
	}

	public OrderDto convertToDto(Order order) {

		OrderDto orderDto = new OrderDto();
		orderDto.setId(order.getId());
		orderDto.setCompleted(order.getCompleted());
		orderDto.setDeliveryDate(order.getDeliveryDate());
		orderDto.setOrderDate(order.getOrderDate());
		orderDto.setTotalPrice(order.getTotalPrice());
		if(order.getUser() != null) {
			orderDto.setUserDto(userMapper.convertToDto(order.getUser()));
		}

		return orderDto;
	}

}
