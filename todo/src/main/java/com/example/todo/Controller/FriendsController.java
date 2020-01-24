package com.example.todo.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import com.example.todo.Repository.UserRepository;

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
	UserRepository userRepository;

	@GetMapping({ "/Friends" })
	public String index(Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c1 = Calendar.getInstance();
		String today = sdf.format(c1.getTime());	
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
				

//			List<Todo> list = todoRepository.findAll();
			List<Todo> list = todoRepository.findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(today, today);
			List<Todo> list_real = new ArrayList<Todo>();
			for (Todo todo : list) {
				if (dbUser.getId() == todo.getUser_id()&& todo.getRange().equals("our"))
					list_real.add(todo);
			}
			model.addAttribute("list_real", list_real);
		

			
			
			List<TodoResult> list2 = todoResultRepository.findAllByToday(today);
//		//	Map<Long, Integer> map_real = new HashMap<Long, Integer>();
//			for (TodoResult todoresult : list2) {
//				long todoId = todoresult.getTodoId();
//				int realCount = todoresult.getRealCount();
//				map_real.put(todoId, realCount);
//			}
//			model.addAttribute("map_real", map_real);
			Map<Long, Long> map_todoId = new HashMap<Long, Long>();
			Map<Long,Integer> map_realCount = new HashMap<Long,Integer>();
			Map<Long,String> map_name = new HashMap<Long,String>();
			for(TodoResult todoresult : list2) {
				long todoId = todoresult.getTodoId();
				long PartyId = todoresult.getPartyId();
				int RealCount = todoresult.getRealCount();
				Optional<Todo> opt = todoRepository.findById(todoId);
				Todo temp=opt.get();
				String Name=temp.getNickName();
				map_todoId.put(todoId,PartyId);
				map_realCount.put(todoId,RealCount);
				map_name.put(todoId,Name);
			}
			model.addAttribute("map_todoId", map_todoId);
			model.addAttribute("map_realCount", map_realCount);
			model.addAttribute("map_name", map_name);
		
		}
		System.out.println("===========================================");
		System.out.println("home");
		System.out.println("===========================================");
		log.error("home ");
		return "/friend";
	}


	
}