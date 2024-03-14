package com.projectx.projectx.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.projectx.projectx.entities.Post;
import com.projectx.projectx.entities.User;
import com.projectx.projectx.services.PostService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    PostService postService;

    @GetMapping("/profile")
    public String showUserprofile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user != null) {

            List<Post> list = postService.findPostsByUserEmail(user.getEmail());

            model.addAttribute("size", list.size());
            model.addAttribute("list", list);
            model.addAttribute("user", user);
            return "user/profile";
        }

        return "redirect:/login";
    }
}
