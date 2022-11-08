package ua.tony.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderNotDeletedException extends RuntimeException {

	public OrderNotDeletedException(String massege) {
		super(massege);
	}
}
