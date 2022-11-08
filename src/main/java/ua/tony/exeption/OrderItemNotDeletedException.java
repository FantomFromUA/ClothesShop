package ua.tony.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderItemNotDeletedException extends RuntimeException {

	public OrderItemNotDeletedException(String massege) {
		super(massege);
	}
}
