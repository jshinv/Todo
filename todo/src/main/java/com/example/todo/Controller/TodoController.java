package com.example.todo.Controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.todo.Model.Invite;
import com.example.todo.Model.Todo;
import com.example.todo.Model.TodoResult;
import com.example.todo.Model.User;
import com.example.todo.Repository.FriendRepository;
import com.example.todo.Repository.InviteRepository;
import com.example.todo.Repository.TodoRepository;
import com.example.todo.Repository.TodoResultRepository;
import com.example.todo.Repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TodoController {
	@Autowired
	UserRepository userRepository;

	@Autowired
	TodoResultRepository todoResultrepository;

	@Autowired
	TodoRepository todoRepository;
	
	@Autowired
	InviteRepository inviteRepository;


	@Autowired
	HttpSession session;

	@Autowired
	FriendRepository friendRepository;

	@GetMapping("/todo")
	public String Todo(Model model) {
		User dbUser = (User) session.getAttribute("user_info");
		if (dbUser != null) {
			List<Todo> list = todoRepository.findAll();
			model.addAttribute("list_real", list);
			model.addAttribute("count", list.size());
			model.addAttribute("userlist", userRepository.findAll());
			model.addAttribute("friendlist", friendRepository.findAll());
		}
		return "cus/todo";
	}

	@PostMapping("/todo")
	public String signupPost(@RequestParam("title") String title, @RequestParam("color") String color,
			@RequestParam("count") int count, @RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate, @RequestParam("range") String range) {
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c1 = Calendar.getInstance();
		String today = sdf.format(c1.getTime());
		
		Todo todo = new Todo();
		User dbUser = (User) session.getAttribute("user_info");
		todo.setUser_id(dbUser.getId());
		todo.setHostId(dbUser.getNickName());
		todo.setSetDate(today);
		todo.setStartDate(startDate);
		todo.setEndDate(endDate);
		todo.setTitle(title);
		todo.setColor(color);
		todo.setGoalCount(count);
		todo.setRange(range);
		todoRepository.save(todo);
        

		TodoResult todoResult = new TodoResult();
		todoResult.setToday(today);
		todoResult.setTodoId(todo.getId());
		todoResult.setRealCount(0);
		todoResultrepository.save(todoResult);
		return "redirect:/";
	}

	@PostMapping("/todo2")
	public String signupPost2(@RequestParam("title") String title, @RequestParam("color") String color,
			@RequestParam("count") int count, @RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate, @RequestParam("range") String range,
			@RequestParam("addname") List<String> addname, Model model) {
		model.addAttribute("usr", addname);
		Todo todo = new Todo();
		User dbUser = (User) session.getAttribute("user_info");
		log.error("todo2");
		todo.setUser_id(dbUser.getId());
		todo.setHostId(dbUser.getNickName());
		todo.setStartDate(startDate);
		todo.setEndDate(endDate);
		todo.setTitle(title);
		todo.setColor(color);
		todo.setGoalCount(count);
		todo.setRange(range);
		todoRepository.save(todo);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c1 = Calendar.getInstance();
		String today = sdf.format(c1.getTime());

		
		for (String idx : addname) {
			Invite invite = new Invite();
			invite.setNickName1(dbUser.getNickName());
			invite.setNickName2(idx);
			invite.setBool1(true);
			invite.setTodoinvite(true);
			Todo temp=todoRepository.findByHostIdAndTitle(dbUser.getNickName(),title);
			invite.setTodo_id(temp.getId());
			inviteRepository.save(invite);
		}
	
		model.addAttribute("friendlist", friendRepository.findAll()); //���� ���� �̻�
		
		TodoResult todoResult = new TodoResult();
		todoResult.setToday(today);
		todoResult.setTodoId(todo.getId());
		todoResult.setRealCount(0);
		todoResultrepository.save(todoResult);
		
		
		return "cus/setting"; //���� ���� ��


	}

	Logger logger = LoggerFactory.getLogger(this.getClass());
}
