package com.projectx.projectx.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectx.projectx.dao.PostRepo;
import com.projectx.projectx.entities.Post;

@Service
public class PostService {

    @Autowired
    PostRepo postRepo;

    // Method to save a post and return its ID
    public Long savePost(Post post) {
        @SuppressWarnings("null")
        Post savedPost = postRepo.save(post);
        return savedPost.getId();
    }
}
