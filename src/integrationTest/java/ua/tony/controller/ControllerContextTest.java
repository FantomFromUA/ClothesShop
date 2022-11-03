package ua.tony.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ControllerContextTest {

    @Autowired
    private UserRestController userController;
    @Autowired
    private ProductRestController productController;
    @Autowired
    private OrderRestController orderController;
    @Autowired
    private OrderItemRestController orderItemController;

    @Test
    public void contextLoads() throws Exception {
	assertThat(userController).isNotNull();
	assertThat(productController).isNotNull();
	assertThat(orderController).isNotNull();
	assertThat(orderItemController).isNotNull();

    }
}

