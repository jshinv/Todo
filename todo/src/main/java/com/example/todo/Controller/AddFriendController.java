package com.example.todo.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.todo.Model.Friend;
import com.example.todo.Model.Invite;
import com.example.todo.Model.User;
import com.example.todo.Repository.FriendRepository;
import com.example.todo.Repository.InviteRepository;
import com.example.todo.Repository.UserRepository;


@Controller
public class AddFriendController {

	@Autowired
	FriendRepository friendRepository;
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	InviteRepository inviteRepository;
	
	@Autowired
	HttpSession session;

	@GetMapping("/addFriend")
	public String addFriend(Model model) {

		model.addAttribute("userlist", userRepository.findAll());
		return "cus/addFriend";
	}

	@PostMapping("/addFriend")
	public String addFriendPost(@RequestParam("usr") String usr) {
		User dbUser = (User) session.getAttribute("user_info");
		Friend friend = new Friend();
		friend.setUsr1(dbUser.getNickName());
		friend.setUsr2(usr);
		friendRepository.save(friend);
		return "cus/addFriend";

	}

	@PostMapping("/invite")
	public String invite(@RequestParam("usr") String usr,Model model) {
		User dbUser = (User) session.getAttribute("user_info");
		Invite invite = new Invite();
		invite.setNickName1(dbUser.getNickName());
		invite.setNickName2(usr);
		invite.setBool1(true);
		invite.setTodoinvite(false);
		inviteRepository.save(invite);
		model.addAttribute("userlist", userRepository.findAll());
		model.addAttribute("friendlist", friendRepository.findAll());
		return "cus/setting";

	}

}
