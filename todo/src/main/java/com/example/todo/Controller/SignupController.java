package com.example.todo.Controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.todo.Model.User;
import com.example.todo.Repository.UserRepository;

@Controller
public class SignupController {

	@Autowired
	UserRepository userRepository;

	@GetMapping("/signup")
	public String signup(Model model) {
		List<User> list = userRepository.findAll();
		List<String> listNickName = new ArrayList<String>();

		for(User tempUser :list) {
			listNickName.add(tempUser.getNickName());
		}
		
		model.addAttribute("userlist",listNickName);
		return "cus/signup";
	}

	@PostMapping("/signup")
	public String signupPost(@ModelAttribute User user,Model model) {
		userRepository.save(user);
		model.addAttribute("userlist",userRepository.findAll());
		
		return "cus/signin";
	}

	Logger logger = LoggerFactory.getLogger(this.getClass());
}