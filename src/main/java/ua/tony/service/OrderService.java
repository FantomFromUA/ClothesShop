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

    public OrderDto save(OrderDto orderDto) {

	Order order = orderMapper.convertToEntity(orderDto);
	return orderMapper.convertToDto(orderRepo.save(order));
    }

    public OrderDto update(OrderDto orderDto) {

	Order order = orderMapper.convertToEntity(orderDto);
	return orderMapper.convertToDto(orderRepo.save(order));
    }

    public OrderDto findById(Integer id) {

	return orderMapper.convertToDto(orderRepo.findById(id).get());
    }

    public List<OrderDto> findByOrderDate(LocalDate orderDate) {

	List<OrderDto> orders = orderRepo.findByOrderDate(orderDate).stream().map(x -> orderMapper.convertToDto(x))
		.toList();
	return orders;
    }

    public List<OrderDto> findByDeliveryDate(LocalDate orderDate) {

	List<OrderDto> orders = orderRepo.findByDeliveryDate(orderDate).stream().map(x -> orderMapper.convertToDto(x))
		.toList();
	return orders;
    }

    public List<OrderDto> getOrdersThatRelatedToUser(Integer id) {

	List<OrderDto> orders = orderRepo.getOrdersThatRelatedToUser(id).stream().map(x -> orderMapper.convertToDto(x))
		.toList();
	return orders;
    }

    public List<OrderDto> findAll() {

	List<OrderDto> orders = orderRepo.findAll().stream().map(x -> orderMapper.convertToDto(x)).toList();
	return orders;
    }

    public void deleteAll() {

	orderRepo.deleteAll();
    }

    public void deleteById(Integer id) {

	orderRepo.deleteById(id);
    }
}