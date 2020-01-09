package com.example.todo.Controller;


import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.todo.Model.Todo;
import com.example.todo.Model.TodoResult;
import com.example.todo.Model.User;
import com.example.todo.Repository.TodoRepository;
import com.example.todo.Repository.TodoResultRepository;
import com.fasterxml.jackson.core.JsonProcessingException;


@Controller
public class CalenderController {

	@Autowired
	TodoRepository todoRepository;
	@Autowired
	TodoResultRepository todoResultRepository;
	@Autowired
	HttpSession session;

	@GetMapping("/getCalenderData")
	@ResponseBody
	public Todo calenderGetData(long id) {
		Optional<Todo> opt = todoRepository.findById(id);
		Todo todo = opt.get();
		return todo;
	}

	@GetMapping("/getCalenderResultData")
	@ResponseBody
	public List<TodoResult> calenderGetResultData(long id) {
		List<TodoResult> list = todoResultRepository.findAllByTodoId(id);
		return list;
	}

	@GetMapping("/calender/{id}")
	public String calenderGet(Model model, @PathVariable("id") long id) throws JsonProcessingException {
		Optional<Todo> data = todoRepository.findById(id);

		Todo todo = data.get();

		model.addAttribute("todo", todo);

		return "calender";
	}

	@PostMapping("/calender")
	public String calenderPost(@ModelAttribute Todo todo) {
		System.out.println("post calendar");
		return "calender";

	}
}