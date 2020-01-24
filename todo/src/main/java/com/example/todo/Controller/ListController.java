package com.example.todo.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ListController {

	@GetMapping("/list")
	public String html() {
		return "index";
		
	}
	Logger logger = LoggerFactory.getLogger(this.getClass());
}