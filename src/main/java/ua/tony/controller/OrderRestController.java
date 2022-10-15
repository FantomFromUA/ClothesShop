package ua.tony.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import ua.tony.dto.OrderDto;
import ua.tony.dto.ProductDto;
import ua.tony.service.OrderService;

@RestController
@RequestMapping("/")
@Tag(name = "Order", description = "this Controller helps client to interact with Orders ")
public class OrderRestController {

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "orders", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<OrderDto> createOrder(@RequestBody @Valid OrderDto orderDto) {

		return new ResponseEntity<>(orderService.save(orderDto), HttpStatus.CREATED);
	}

	@RequestMapping(value = "orders", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<OrderDto> updateOrder(@RequestBody @Valid OrderDto orderDto) {

		return new ResponseEntity<>(orderService.update(orderDto), HttpStatus.CREATED);
	}

}
