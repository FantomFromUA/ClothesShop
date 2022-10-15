package ua.tony.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import ua.tony.service.OrderService;

@RestController
@RequestMapping("/")
@Tag(name = "Order", description = "this Controller helps client to interact with Orders ")
public class OrderRestController {

    @Autowired
    private OrderService orderService;
    
}
