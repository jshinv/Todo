package com.example.todo.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.todo.Model.Todo;
import com.example.todo.Model.TodoResult;
import com.example.todo.Model.User;
import com.example.todo.Repository.TodoRepository;
import com.example.todo.Repository.TodoResultRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TodoResultController {
	@Autowired
	TodoResultRepository todoResultRepository;
	
	@Autowired
	TodoRepository todoRepository;


	@Autowired
	HttpSession session;

	@GetMapping("/todo_result")
	public String Todo_result() {
		return "cus/todo_result";
	}
	
	
	@ResponseBody
	@PostMapping("/todo_result")
	public Map<String, Object> Todo_resultPost(@RequestParam("realCount") int realCount, @RequestParam("todo_id") long todo_id) {
		TodoResult todoResult = new TodoResult();

		
		todoResult.setTodo_id(todo_id);
		todoResult.setToday("123123");

		int total_value = todoResult.getRealCount();
		int hh = total_value + realCount;
		todoResult.setRealCount(hh);
		
		todoResultRepository.save(todoResult);
		
		Map<String, Object> res = new HashMap<String, Object>();
		return res;
	}


	Logger logger = LoggerFactory.getLogger(this.getClass());
}


