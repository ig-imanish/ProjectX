package com.projectx.projectx.services;

import java.util.Optional;

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

    public void removePost(Long id) {
        if (id != null) {
            postRepo.deleteById(id);
        }
        System.out.println("Id is null " + id);
    }

    public Optional<Post> findById(Long id) {
        Optional<Post> optional = null;
        if(id != null){

            optional = postRepo.findById(id);
        }
        return optional;
    }

    public void delete(Post existingPost) {
        if(existingPost != null){

            postRepo.delete(existingPost);
        }
        System.out.println("Error while deleting " + existingPost);
    }
}
