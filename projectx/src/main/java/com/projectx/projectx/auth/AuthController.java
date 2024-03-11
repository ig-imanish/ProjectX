package com.projectx.projectx.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projectx.projectx.entities.User;
import com.projectx.projectx.helpers.Message;
import com.projectx.projectx.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    Message message;

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public String signup(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login"; // Redirect to login if user not logged in
        }
        return "redirect:/index"; // Redirect to index page without the /auth/user URL
    }

}