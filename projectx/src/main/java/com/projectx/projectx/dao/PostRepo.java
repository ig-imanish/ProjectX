package com.projectx.projectx.dao;


import org.springframework.data.repository.CrudRepository;
import com.projectx.projectx.entities.Post;
public interface PostRepo extends CrudRepository<Post, Long>{
    
}
