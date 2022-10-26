package ua.tony.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import ua.tony.dto.OrderDto;
import ua.tony.service.OrderService;

@RestController
@RequestMapping("/")
@Tag(name = "Order", description = "this Controller helps client to interact with Orders ")
public class OrderRestController {

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "orders", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	@Operation(summary = "1. Helps to get order by its id " + "2. Helps to get all orders")
	public ResponseEntity<List<OrderDto>> getOrderById(
			@RequestParam(value = "order_id", required = false) Integer order_id) {
		if (order_id == null) {
			return ResponseEntity.ok(orderService.findAll());
		}

		List<OrderDto> orders = new ArrayList<>();
		orders.add(orderService.findById(order_id));
		return ResponseEntity.ok(orders);

	}

	@RequestMapping(value = "orders", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	@Operation(summary = "Helps to create new orders")
	public ResponseEntity<OrderDto> createOrder(@RequestBody @Valid OrderDto orderDto) {

		return new ResponseEntity<>(orderService.save(orderDto), HttpStatus.CREATED);
	}

	@RequestMapping(value = "orders", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	@Operation(summary = "Helps to update already exists order")
	public ResponseEntity<OrderDto> updateOrder(@RequestBody @Valid OrderDto orderDto) {

		return new ResponseEntity<>(orderService.update(orderDto), HttpStatus.CREATED);
	}

	@RequestMapping(value = "orders", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	@Operation(summary = "1. Helps to delete order by uts id" + "2. Helps to delete all the orders")
	public ResponseEntity<HttpStatus> deleteOrderByIdOrAll(
			@RequestParam(value = "order_id", required = false) Integer order_id) {
		if (order_id == null) {
			orderService.deleteAll();
		} else {
			orderService.deleteById(order_id);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);

	}
}
