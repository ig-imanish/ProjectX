package com.projectx.projectx.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user_db")
public class User {

    @Id
    @Email(message = "Enter a valid email!")
    private String email;
    @Size(min = 3, max = 15, message = "Please Enter Name Of 3-15 Size!")
    private String nickname;
    @NotBlank(message = "Provide a password")    
    private String password;
    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    private List<Post> posts;

    public boolean isEmpty() {
        return nickname == null || nickname.isEmpty() ||
                email == null || email.isEmpty() ||
                password == null || password.isEmpty();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [nickname=" + nickname + ", email=" + email + ", password=" + password + "]";
    }

    public User() {
    }

    public User(String nickname, String email, String password) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}