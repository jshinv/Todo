package com.example.todo.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.todo.Repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SigninController {

	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/signin")
	public String html() {
		return "cus/signIn";
		
	}
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
}