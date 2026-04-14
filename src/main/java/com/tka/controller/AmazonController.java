package com.tka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AmazonController {
	
//	@GetMapping("/")
//	public String firstPage() {
//		return "index";
//	}
	
	@GetMapping("/login-page")
	public String getLoginPage() {
		return "login";
	}
	
//	@GetMapping("/get-signup-page")
//	public String getSignupPage() {
//		return "register";
//	}
	
	@GetMapping("/add-product")
	public String getAddProductPage() {
		return "addProduct";
	}

}
