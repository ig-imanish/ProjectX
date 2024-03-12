package com.projectx.projectx.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projectx.projectx.dao.PostRepo;
import com.projectx.projectx.entities.Post;
import com.projectx.projectx.entities.User;
import com.projectx.projectx.helpers.Message;
import com.projectx.projectx.helpers.MethodsForGen;
import com.projectx.projectx.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

    @Autowired
    Message message;

    @Autowired
    MethodsForGen methodsForGen;

    @Autowired
    UserService userService;

    @Autowired
    PostRepo postRepo;

    @GetMapping("/")
    public String home(HttpSession session) {
    User user = (User) session.getAttribute("user");

    if (user != null) {
        return "/auth/index";
    }
    return "redirect:/login";
    }


    @GetMapping("/index")
    public String index(HttpSession session , Model model) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            // If the user is logged in, fetch posts from the repository
            Iterable<Post> postIterable = postRepo.findAll();
            List<Post> posts = new ArrayList<>();
            postIterable.forEach(posts::add);
            model.addAttribute("posts", posts);
            model.addAttribute("user", user);
            System.out.println("post " + posts);
            return "index";
        }
        return "redirect:/login";
    }

    @RequestMapping("/login")
    public String login(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
            return "redirect:/index";
        }
        model.addAttribute("user", new User());
        return "/auth/login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpSession session) {
        User existingUser = userService.getUserByEmail(email);
        if (existingUser != null && existingUser.getPassword().equals(password)) {
            session.setAttribute("user", existingUser);
            return "redirect:/index"; 
        } else {
            return "redirect:/login?error";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        return "/auth/logout";
    }

    @PostMapping("/logout")
    public String logoutPostMapping(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/signup")
    public String signup(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return "redirect:/index"; // Redirect to the index page if user is already logged in
        }
        model.addAttribute("user", new User());
        return "/auth/signup";
    }

    @PostMapping("/do_register")
    public String registerSignup(@ModelAttribute("user") User user,
            BindingResult bindingResult,
            Model model,
            HttpSession session) {
        if (user.getEmail().isEmpty()) {
            model.addAttribute("noEmailMessage", message.noFieldProvided("email"));
            return "/auth/signup";
        }
        if (user.getPassword().isEmpty()) {
            model.addAttribute("noPasswordMessage", message.noFieldProvided("password"));
            return "/auth/signup";
        }
        // Validate form fields
        if (bindingResult.hasErrors()) {
            // Handle validation errors
            return "/auth/signup";
        }

        System.out.println("main " + user);
        userService.insertUser(user);
        session.setAttribute("user", user);

        return "redirect:/auth/user";
    }
}
