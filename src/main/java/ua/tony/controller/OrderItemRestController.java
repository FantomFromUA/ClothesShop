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
import ua.tony.dto.OrderItemDto;
import ua.tony.service.OrderItemService;

@RestController
@RequestMapping("/")
@Tag(name = "OrderItem", description = "this Controller helps client to interact with Orders ")
public class OrderItemRestController {

    @Autowired
    private OrderItemService orderItemService;

    @RequestMapping(value = "orderItems", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    @Operation(summary = "1. Helps to get all order items" + "2. Helps to get order item by its id"
	    + "3. Helps to get order item by product id" + "4. Helps to get order items by order id")
    public ResponseEntity<List<OrderItemDto>> getOrderItemByIdOrByProductIdOrByOrderIdOrGetAll(
	    @RequestParam(value = "order_item_id", required = false) Integer orderItemId,
	    @RequestParam(value = "product_id", required = false) Integer productId,
	    @RequestParam(value = "order_id", required = false) Integer orderId) {
	if (orderItemId == null && productId == null && orderId == null) {
	    return ResponseEntity.ok(orderItemService.findAll());
	}

	if (orderItemId != null && productId == null && orderId == null) {
	    List<OrderItemDto> orderItems = new ArrayList<>();
	    orderItems.add(orderItemService.findById(orderItemId));
	    return ResponseEntity.ok(orderItems);
	}

	if (orderItemId == null && productId != null && orderId == null) {
	    List<OrderItemDto> orderItems = new ArrayList<>();
	    orderItems.add(orderItemService.findByProductId(productId));
	    return ResponseEntity.ok(orderItems);
	}

	if (orderItemId == null && productId == null && orderId != null) {
	    return ResponseEntity.ok(orderItemService.findByOrderId(orderId));
	}

	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @RequestMapping(value = "orderItems", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    @Operation(summary = "Helps to create new order item")
    public ResponseEntity<OrderItemDto> createOrderItem(@RequestBody @Valid OrderItemDto ordetItemDto) {
	return new ResponseEntity<>(orderItemService.save(ordetItemDto), HttpStatus.CREATED);
    }

    @RequestMapping(value = "orderItems", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    @Operation(summary = "Helps to update already exosts order item")
    public ResponseEntity<OrderItemDto> updateOrderItem(@RequestBody @Valid OrderItemDto orderItemDto) {
	return new ResponseEntity<>(orderItemService.update(orderItemDto), HttpStatus.CREATED);
    }

    @RequestMapping(value = "orderItems", method = RequestMethod.DELETE, produces = {
	    MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    @Operation(summary = "1. Helps to delete all order items" + "2. Helps to delete order by its id")
    public ResponseEntity<HttpStatus> deleteOrderItemByIdOrAll(
	    @RequestParam(value = "order_item_id", required = false) Integer orderItemId) {
	if (orderItemId == null) {
	    orderItemService.deleteAll();
	} else {
	    orderItemService.deleteById(orderItemId);
	}

	return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }
}
