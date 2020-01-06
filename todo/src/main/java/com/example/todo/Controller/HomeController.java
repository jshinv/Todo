package com.example.todo.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.todo.Model.Todo;
import com.example.todo.Model.User;
import com.example.todo.Repository.TodoRepository;

@Controller
public class HomeController {
	
//	�씠寃� �엳�뼱�빞 todorepository�쓽 硫붿냼�뱶 �궗�슜媛�
	@Autowired
	TodoRepository todoRepository;
	
	@Autowired
	HttpSession session;
	
	@GetMapping({ "/", "/home" })
	public String index(Model model) {
		User dbUser = (User) session.getAttribute("user_info");
		if(dbUser!=null) {
			List<Todo> list = todoRepository.findAll();
			List<Todo> list_real= new ArrayList<Todo>();
			for(Todo todo :list) {
				if(dbUser.getId()==todo.getUser_id())
					list_real.add(todo);
			}
			model.addAttribute("list_real",list_real);
		}
		return "index";
	}}