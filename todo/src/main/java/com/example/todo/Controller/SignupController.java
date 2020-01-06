package com.example.todo.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.todo.Model.User;
import com.example.todo.Repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Controller
public class SignupController {

	@Autowired
	UserRepository userRepository;

	@GetMapping("/signup")
	public String signup() {
		return "cus/signup";
	}

	@PostMapping("/signup")
	public String signupPost(@ModelAttribute User user) {
		userRepository.save(user);
		return "cus/signIn";
	}
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
}