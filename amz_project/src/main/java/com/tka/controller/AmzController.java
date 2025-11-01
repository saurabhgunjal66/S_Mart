package com.tka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AmzController {
    @GetMapping("/")
    public String home() {
    	System.out.println("get");
        return "index";  // will look for index.jsp or index.html
    }
    
    @GetMapping("/get-loginPage")
    public String LoginPage() {
    	System.out.println(">> FirstloginPage");
        return "login";  // return view resolver
    }
    
    @GetMapping("/get-signUpPage")
    public String signupPage() {
    	System.out.println(">> FirstSignUpPage");
        return "register";  // return view resolver
    }
    
    

    
}

