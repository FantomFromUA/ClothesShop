package ua.tony.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ua.tony.entity.User;
import ua.tony.repository.UserRepository;

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
	
	@GetMapping(value="/myProfile")
	public String showMyProfile() {
	    return "user_page";
	}
}