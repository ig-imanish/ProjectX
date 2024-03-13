package com.projectx.projectx.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    
    @GetMapping("/profile")
    public String showUserprofile(){
        return "/user/profile.html";
    }
}
