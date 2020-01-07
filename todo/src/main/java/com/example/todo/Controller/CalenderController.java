package com.example.todo.Controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.todo.Model.Calender;
import com.example.todo.Model.Todo;
import com.example.todo.Model.User;
import com.example.todo.Repository.CalenderRepository;
import com.example.todo.Repository.TodoRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class CalenderController {
	@Autowired
	CalenderRepository calenderRepository;
	@Autowired
	TodoRepository todoRepository;
	
	@GetMapping("/calender")
	public String calenderGet() {
		return "calender";
		
	}
	@PostMapping("/calender")
	public String calenderPost(@ModelAttribute Todo todo) {	
	
		return "calender";
	
	}
}