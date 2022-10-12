package ua.tony.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import ua.tony.dto.OrderItemDto;
import ua.tony.entity.OrderItem;

@Component
public class OrderItemMapper {

    private ProductMapper productMapper;
    private OrderMapper orderMapper;

    @Autowired
    public OrderItemMapper(@Lazy ProductMapper productMapper, @Lazy OrderMapper orderMapper) {
	this.productMapper = productMapper;
	this.orderMapper = orderMapper;

    }

    public OrderItem convertToEntity(OrderItemDto orderItemDto) {

	OrderItem orderItem = new OrderItem();
	orderItem.setId(orderItemDto.getId());
	orderItem.setOrder(orderMapper.convertToEntity(orderItemDto.getOrderDto()));
	orderItem.setProduct(productMapper.convertToEntity(orderItemDto.getProductDto()));

	return orderItem;
    }

    public OrderItemDto convertToDto(OrderItem orderItem) {

	OrderItemDto orderItemDto = new OrderItemDto();
	orderItemDto.setId(orderItem.getId());
	orderItemDto.setOrderDto(orderMapper.convertToDto(orderItem.getOrder()));
	orderItemDto.setProductDto(productMapper.convertToDto(orderItem.getProduct()));

	return orderItemDto;
    }
}
