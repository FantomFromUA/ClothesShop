package ua.tony.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ua.tony.entity.User;
import ua.tony.repository.UserRepository;

@Controller
public class ConfirmEmailController {
	
	@Autowired
	UserRepository userRepo;

	@GetMapping(value = "registration/confirm")
    public String confirm(@RequestParam("token") String token) {
		User user = userRepo.findByToken(token);
		
		user.setAvailable(true);
		
		userRepo.save(user);
		
		return "login";
    }
}
