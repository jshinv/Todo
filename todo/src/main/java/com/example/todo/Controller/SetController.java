package com.example.todo.Controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.todo.Repository.TodoRepository;
import com.example.todo.Repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SetController {
	@Autowired
	UserRepository userRepository;
	TodoRepository todoRepository;

	@Autowired
	HttpSession session;
	
	@GetMapping("/setting")
	public String html() {
		return "cus/setting";
		
	}
	


	
	
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
}