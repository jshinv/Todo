package com.example.todo.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.todo.Model.Todo;
import com.example.todo.Model.TodoResult;
import com.example.todo.Model.User;
import com.example.todo.Repository.TodoRepository;
import com.example.todo.Repository.TodoResultRepository;
import com.example.todo.Repository.UserRepository;



@Controller
public class SetController {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TodoResultRepository todoResultRepository;
	
	@Autowired
	TodoRepository todoRepository;
	
	@Autowired
	HttpSession session;

	
	@GetMapping("/setting")
	public String html(Model model) {
		User dbUser = (User) session.getAttribute("user_info");
		if(dbUser!=null) {
			List<Todo> list = todoRepository.findAll();
			model.addAttribute("list_real",list);
		}
		return "cus/setting";
		
	}


	Logger logger = LoggerFactory.getLogger(this.getClass());
}