package com.example.todo.Controller;

import javax.servlet.http.HttpSession;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.todo.Model.Todo;
import com.example.todo.Model.TodoResult;
import com.example.todo.Model.User;
import com.example.todo.Repository.TodoRepository;
import com.example.todo.Repository.TodoResultRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TodoController {
	@Autowired
	TodoRepository todoRepository;

	@Autowired
	TodoResultRepository todoResultrepository;
	
	@Autowired
	HttpSession session;

	@GetMapping("/todo")
	public String Todo() {
		return "cus/todo";
	}

	@PostMapping("/todo")
	public String signupPost(@RequestParam("title") String title, @RequestParam("color") String color,
			@RequestParam("count") int count, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate){
		Todo todo = new Todo();
		User dbUser = (User) session.getAttribute("user_info");
		todo.setUser_id(dbUser.getId());
		todo.setHostId(dbUser.getNickName());
		todo.setStartDate(startDate);
		todo.setEndDate(endDate);
		todo.setTitle(title);
		todo.setColor(color);
		todo.setCount(count);
		todoRepository.save(todo);
		
		TodoResult todoResult = new TodoResult();
		todoResult.setRealCount(0);
		todoResult.setToday("123123");
		for (int i = 1; i < 100; i += 1) {
			todoResult.setTodo_id(i);
		}
		todoResultrepository.save(todoResult);
		return "redirect:/";
	}

	Logger logger = LoggerFactory.getLogger(this.getClass());
}


