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
public class HomeController {

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

	@GetMapping({ "/", "/home" })
	public String index(Model model, TodoResult todoResult) {
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
				if (dbUser.getId() == todo.getUser_id())
					list_real.add(todo);
			}
			model.addAttribute("list_real", list_real);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c1 = Calendar.getInstance();
			String today = sdf.format(c1.getTime());

			List<TodoResult> list2 = todoResultRepository.findAllByToday(today);
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
		return "index";

	}

	@PostMapping("/home")
	public String indexPost(@RequestParam("confirmflag") boolean confirmflag, @RequestParam("invite") long invite,
			@RequestParam("todoinvite") boolean todoinvite) {
		Invite invite2 = inviteRepository.findById(invite);

		log.error("home controller");
		log.error("home controller confirmflag " + confirmflag);
		if (confirmflag) {
			log.error("confirmflag todoinvite " + todoinvite);
			if (!todoinvite) {
				Friend friend = new Friend();
				friend.setUsr1(invite2.getNickName1());
				friend.setUsr2(invite2.getNickName2());
				friendeRepository.save(friend);

			} else {
				log.error("confirmflag else ");
				Todo todo = new Todo();

				User dbUser = (User) session.getAttribute("user_info");

				List<Todo> list = todoRepository.findAll();
				for (Todo temp : list) {
					if (temp.getId() == invite2.getTodo_id()) {

						todo.setStartDate(temp.getStartDate());
						todo.setEndDate(temp.getEndDate());
						todo.setColor(temp.getColor());
						todo.setUser_id(dbUser.getId());
						todo.setHostId(invite2.getNickName1());
						todo.setParty_ID(temp.getId());
						todo.setRange(temp.getRange());
						todo.setGoalCount(temp.getGoalCount());
						todo.setTitle(temp.getTitle());
						temp.setParty_ID(temp.getId());
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Calendar c1 = Calendar.getInstance();
						String today = sdf.format(c1.getTime());

						TodoResult todoResult = new TodoResult();
						todoResult.setToday(today);
						todoResult.setTodoId(todo.getId());
						todoResult.setRealCount(0);
						todoResultrepository.save(todoResult);
						break;
					}

				}

//				占쏙옙占싸댐옙占�
				System.out.println("===========================================");
				todoRepository.save(todo);
				System.out.println("===========================================");
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//				Calendar c1 = Calendar.getInstance();
//				
//				String today = sdf.format(c1.getTime());
//				TodoResult todoResult = new TodoResult();
//				todoResult.setToday(today);
//				todoResult.setTodoId(todo.getId());
//				todoResult.setRealCount(0);
//				todoResultrepository.save(todoResult);

			}
		}
		inviteRepository.delete(invite2);
		return "redirect:/";
	}
}