package com.example.todo.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.todo.Model.Friend;
import com.example.todo.Model.Invite;
import com.example.todo.Model.Todo;
import com.example.todo.Model.TodoResult;
import com.example.todo.Model.User;
import com.example.todo.Repository.FriendRepository;
import com.example.todo.Repository.InviteRepository;
import com.example.todo.Repository.TodoRepository;
import com.example.todo.Repository.TodoResultRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class FriendsController {

	@Autowired
	TodoRepository todoRepository;
	@Autowired
	TodoResultRepository todoResultRepository;
	@Autowired
	InviteRepository inviteRepository;
	@Autowired
	FriendRepository friendeRepository;
	@Autowired
	HttpSession session;
	@Autowired
	TodoResultRepository todoResultrepository;

	@GetMapping({ "/Friends" })
	public String index(Model model) {
		User dbUser = (User) session.getAttribute("user_info");
		if (dbUser != null) {
			List<Invite> inviteUserList = inviteRepository.findAll();
			for (Invite temp : inviteUserList) {
				if (temp.getNickName2() == dbUser.getNickName()) {
					if (!temp.isBool2()) {
						model.addAttribute("inviteinform", temp);
						break;
					}

				}
			}
			
			List<Todo> list = todoRepository.findAll();
			List<Todo> list_real = new ArrayList<Todo>();
			for (Todo todo : list) {
				if (dbUser.getId() == todo.getUser_id()&& todo.getRange().equals("our"))
					list_real.add(todo);
			}
			model.addAttribute("list_real", list_real);

			List<TodoResult> list2 = todoResultRepository.findAll();
			Map<Long, Integer> map_real = new HashMap<Long, Integer>();
			for (TodoResult todoresult : list2) {
				long todoId = todoresult.getTodoId();
				int realCount = todoresult.getRealCount();
				map_real.put(todoId, realCount);
			}
			model.addAttribute("map_real", map_real);
	
		}
		System.out.println("===========================================");
		System.out.println("home");
		System.out.println("===========================================");
		log.error("home ");
		return "/friend";
	}


	
}