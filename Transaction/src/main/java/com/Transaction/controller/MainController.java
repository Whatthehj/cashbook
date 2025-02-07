package com.Transaction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
	
	@GetMapping("/main")
	    public String showMainPage() {
	        return "mainPage"; // "templates/main.html"을 렌더링
	    }
}
