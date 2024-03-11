package com.projectx.projectx.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projectx.projectx.dao.PostRepo;
import com.projectx.projectx.entities.Post;
import com.projectx.projectx.entities.User;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    PostRepo postRepo;


    // GET endpoint to show the form for creating a new post
    @GetMapping("/create")
    public String showCreatePostForm(Model model) {
        model.addAttribute("post", new Post());
        return "post_create"; // Assuming you have a Thymeleaf template named "create-post.html"
    }

    // @PostMapping("/create")
    // public String createPost(@RequestParam("content") String content, RedirectAttributes redirectAttributes,
    //         HttpSession session) {
    //     Post post = new Post();
    //     post.setContent(content);

    //     // Retrieve the logged-in user from the session
    //     User user = (User) session.getAttribute("user");

    //     // Assuming you have a logged-in user
    //     if (user != null) {
    //         post.setUser(user);
    //         postRepo.save(post);
    //         redirectAttributes.addFlashAttribute("successMessage", "Post created successfully!");
    //     } else {
    //         redirectAttributes.addFlashAttribute("errorMessage", "Error creating post. Please log in.");
    //     }

    //     return "redirect:/index"; // Redirect to the home page or any other page
    // }

    @PostMapping("/create")
    public String createPost(@ModelAttribute("post") Post post, RedirectAttributes redirectAttributes,
            HttpSession session) {
        User user = (User) session.getAttribute("user");
        post.setUser(user);

        // Assuming you have a logged-in user
        if (user != null) {
            post.setUser(user);
            postRepo.save(post);
            redirectAttributes.addFlashAttribute("successMessage", "Post created successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Error creating post. Please log in.");
        }

        return "redirect:/index"; // Redirect to the home page or any other page
    }
}
