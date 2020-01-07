package com.example.todo.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SetController {

	@GetMapping("/setting")
	public String html() {
		return "cus/setting";
		
	}
	Logger logger = LoggerFactory.getLogger(this.getClass());
}