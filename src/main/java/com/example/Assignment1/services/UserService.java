package com.example.Assignment1.services;

import com.example.Assignment1.model.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class UserService {

  User alice = new User(123, "abc", "def", "ghi");
  User bob = new User(456, "bob", "jkl", "mno");
  User carl = new User(789, "carl", "pqr", "stu");
  User[] users = {alice, bob, carl};


  @GetMapping("/api/user")
  public User[] findAllUsers() {
    return users;
  }

  @GetMapping("/api/user/{userId}")
  public  User findUserById(@PathVariable("userId") Integer id) {
    for(User user : users) {
      if (id == user.getId().intValue()) {
        return user;
      }
    }
    return null;
  }

  @PostMapping("/api/user/{userId}")
  public void createUser(@PathVariable("username") String username) {
    User newUser = new User(110, username, "asd", "asd");
    users[users.length] = newUser;
  }

  public void deleteUser(Integer id) {
    for(User user : users) {
      if(id == user.getId().intValue()) {
      }
    }
  }
//
//  public User updateUser(Integer id, User user) {
//
//  }
}


