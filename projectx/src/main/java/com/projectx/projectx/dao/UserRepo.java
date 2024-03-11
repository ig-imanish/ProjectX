package com.projectx.projectx.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projectx.projectx.entities.User;


@Repository
public interface UserRepo extends CrudRepository<User, String> {

    public User findByEmail(String email);
    
}
