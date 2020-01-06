package com.example.todo.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.todo.Model.User;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class CalenderController {

	@GetMapping("/calender")
	public String html() {
		return "calender";
		
	}
	@PostMapping("/calender")
	public String calenderPost() {
		
		return "calender";
	
	}
	Logger logger = LoggerFactory.getLogger(this.getClass());
}