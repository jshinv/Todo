package com.example.todo.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

	@PostMapping("/todo_result")
	public Object Todo_resultPost(@RequestParam("realCount") String realCount, 
			@RequestParam("todo_id") String todo_id) {
		int int_count=Integer.parseInt(realCount);
		TodoResult todoResult = new TodoResult();
		User dbUser = (User) session.getAttribute("user_info");
		long key = dbUser.getId();
		long real =todoRepository.findById(key);
		todoResult.setTodo_id(real);
		todoResult.setToday("123123");
		todoResult.setRealCount(int_count);
		todoResultRepository.save(todoResult);
		
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("code", "OK");
        res.put("message", "등록에 성공 하였습니다.");
		return "res";
	}


	Logger logger = LoggerFactory.getLogger(this.getClass());
}


