package com.example.todo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.todo.Model.Todo;
import com.example.todo.Repository.TodoRepository;

@Controller
public class HomeController {
	
//	이게 있어야 todorepository의 메소드 사용가
	@Autowired
	TodoRepository todoRepository;
	
	@GetMapping({ "/", "/home" })
	public String index(Model model) {
		List<Todo> list = todoRepository.findAll();
		model.addAttribute("list", list);
		return "index";
	}}