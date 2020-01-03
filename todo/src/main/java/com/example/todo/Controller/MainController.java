package com.example.todo.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController {

	@GetMapping("index")
	public String html() {
		return "index";
	}

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/")
	public String index() {
		log.trace("trace!");
		log.debug("debug!"); // 개발단계에서 확인용
		log.info("info!"); // 운용 상 필요한 정
		log.warn("warn!"); // 메모리 문제 등 경고
		log.error("error!"); // 치명적인 오류

		return "index";
	}
}
