package com.example.todo.Controller;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.todo.Model.TodoResult;
import com.example.todo.Repository.TodoRepository;
import com.example.todo.Repository.TodoResultRepository;


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
	public Map<String, Object> Todo_resultPost(@RequestParam("count") int count, @RequestParam("todo_id") long todo_id) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c1 = Calendar.getInstance();
		String today = sdf.format(c1.getTime());		
		
		TodoResult todoResult1 = todoResultRepository.findByTodoIdAndToday(todo_id, today);

		if (todoResult1 == null) {
			TodoResult todoResult2 = new TodoResult();
			todoResult2.setToday(today);
			todoResult2.setTodoId(todo_id);
			todoResult2.setRealCount(count);
			todoResultRepository.save(todoResult2);
		} else {
			int realCount = todoResult1.getRealCount() + count;
			todoResult1.setRealCount(realCount);
			
			todoResultRepository.save(todoResult1);
		};
		
		Map<String, Object> res = new HashMap<String, Object>();
		return res;
	}


	Logger logger = LoggerFactory.getLogger(this.getClass());
}


