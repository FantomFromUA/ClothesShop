package ua.tony.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageRedirectController {

	/**
	 * Метод який перенаправляє користувача на сторінку Реєстрації
	 * 
	 * @return signup.html (сторінка реєстрації)
	 */
	@GetMapping(value = "/signup")
	public String showSignUp() {
		return "signup";
	}

	/**
	 * Метод який перенаправляє користувача на сторінку Авторизації
	 * 
	 * @return login.html (сторінка авторизації)
	 */
	@GetMapping(value = "/login")
	public String showLoginUp() {
		return "login";
	}
}
