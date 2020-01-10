package com.example.todo.Controller;



import javax.servlet.http.HttpSession;

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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SigninController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	HttpSession session;

	@GetMapping("/signin")
	public String signin(Model model) {
		
		log.error("signin1");
		model.addAttribute("userlist",userRepository.findAll());
		return "cus/signin";
	}

	@PostMapping("/signin")
	public String signinPost(@ModelAttribute User user) {
		User dbUser = userRepository.findByNickNameAndPassWord(user.getNickName(),user.getPassWord());
		if (dbUser != null) {
			session.setAttribute("user_info", dbUser);
			
		}
		log.error("signin2");
		return "redirect:/";
	
	}

	@GetMapping("/signout")
	public String signout() {
		session.invalidate();
		return "redirect:/";
	}

	Logger logger = LoggerFactory.getLogger(this.getClass());
}