package ua.tony.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageRedirectController {

	/**
	 * ����� ���� ������������� ����������� �� ������� ���������
	 * 
	 * @return signup.html (������� ���������)
	 */
	@GetMapping(value = "/signup")
	public String showSignUp() {
		return "signup";
	}

	/**
	 * ����� ���� ������������� ����������� �� ������� �����������
	 * 
	 * @return login.html (������� �����������)
	 */
	@GetMapping(value = "/login")
	public String showLoginUp() {
		return "login";
	}

	@GetMapping(value = "/myProfile")
	public String showMyProfile() {
		return "user_page";
	}

	@GetMapping(value = "/hoodiePage")
	public String showHoodies() {
		return "hoodie_page";
	}
}