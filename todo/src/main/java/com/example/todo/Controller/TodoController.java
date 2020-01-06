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
import com.example.todo.Model.User;
import com.example.todo.Repository.TodoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TodoController {
	@Autowired
	TodoRepository todoRepository;

	@Autowired
	HttpSession session;

	@GetMapping("/todo")
	public String Todo() {
		return "cus/todo";
	}

	@PostMapping("/todo")
	public String signupPost(@RequestParam("title") String title, @RequestParam("color") String color,
			@RequestParam("count") String count) {
		int int_count=Integer.parseInt(count);
		Todo todo = new Todo();
		User dbUser = (User) session.getAttribute("user_info");
		todo.setUser_id(dbUser.getId());
		todo.setHostId(dbUser.getNickName());
		todo.setStartDate("123123");  //罹섎┛�뜑濡� �궇吏쒕컺�븘�삤
		todo.setTitle(title);
		todo.setColor(color);
		todo.setCount(int_count);
		todoRepository.save(todo);
		return "redirect:/";
	}

	Logger logger = LoggerFactory.getLogger(this.getClass());
}


