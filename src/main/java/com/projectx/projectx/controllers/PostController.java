package com.projectx.projectx.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projectx.projectx.dao.PostRepo;
import com.projectx.projectx.entities.Post;
import com.projectx.projectx.entities.User;
import com.projectx.projectx.services.PostService;

import java.util.Optional;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    PostService postService;


    // GET endpoint to show the form for creating a new post
    @GetMapping("/create")
    public String showCreatePostForm(Model model) {
        model.addAttribute("post", new Post());
        return "/posts/post_create"; // Assuming you have a Thymeleaf template named "create-post.html"
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute("post") Post post, RedirectAttributes redirectAttributes,
            HttpSession session) {
        User user = (User) session.getAttribute("user");
        post.setUser(user);

        // Assuming you have a logged-in user
        if (user != null) {
            post.setUser(user);
            postService.savePost(post);
            redirectAttributes.addFlashAttribute("successMessage", "Post created successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Error creating post. Please log in.");
        }

        return "redirect:/index"; // Redirect to the home page or any other page
    }

    @GetMapping("/edit/{id}")
    public String showEditPostForm(@PathVariable("id") Long id, Model model) {
        Optional<Post> optionalPost = postService.findById(id);
        optionalPost.ifPresent(post -> model.addAttribute("post", post));
        return "/posts/post_edit"; // Assuming you have a Thymeleaf template named "post_edit.html"
    }

    @PostMapping("/edit/{id}")
    public String editPost(@PathVariable("id") Long id, @ModelAttribute("post") Post updatedPost,
            RedirectAttributes redirectAttributes,
            HttpSession session) {
        // Retrieve the existing post from the database
        Optional<Post> optionalPost = postService.findById(id);

        if (optionalPost.isPresent()) {
            Post existingPost = optionalPost.get();
            existingPost.setContent(updatedPost.getContent());
            postService.savePost(existingPost);
            redirectAttributes.addFlashAttribute("successMessage", "Post updated successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Error updating post. Post not found.");
        }
        return "redirect:/index";
    }


    @GetMapping("/delete/{id}")
    public String showDeletePostForm(@PathVariable("id") Long id, Model model) {
        Optional<Post> optionalPost = postService.findById(id);
        optionalPost.ifPresent(post -> model.addAttribute("post", post));
        return "/posts/post_delete"; // Assuming you have a Thymeleaf template named "post_edit.html"
    }

    @PostMapping("/delete/{id}")
    public String deletePost(@PathVariable("id") Long id,
            RedirectAttributes redirectAttributes,
            HttpSession session) {
        // Retrieve the existing post from the database
        Optional<Post> optionalPost = postService.findById(id);

        if (optionalPost.isPresent()) {
            Post existingPost = optionalPost.get();
            postService.delete(existingPost);
            redirectAttributes.addFlashAttribute("successMessage", "Post Deleted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting post. Post not found.");
        }
        return "redirect:/index";
    }

}
