package com.example.Assignment1.services;

import com.example.Assignment1.model.Role;
import com.example.Assignment1.model.User;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
public class UserService {



  User alice = new User("123", "abc", "ppp",
          "def", "ghi", Role.FACULTY);
  User bob = new User("456", "bob", "qqq",
          "jkl", "mno", Role.STUDENT);
  User carl = new User("789", "carl", "rrr",
          "pqr", "stu", Role.ADMIN);

  List<User> l = new ArrayList(){{
    add(alice);
    add(bob);
    add(carl);
  }};



  @GetMapping("/api/user")
  public List findAllUsers() {
    return l;
  }

  @GetMapping("/api/user/{userId}")
  public User findUserById(@PathVariable("userId") String id) {
    for(User user : l) {
      if (id.equals(user.getId())) {
        return user;
      }
    }
    return null;
  }


  @PostMapping("/create")
  public String createUser(@RequestBody User userObj) {
//    User newUser = new User(110, username, "asd", "asd");
//    users[users.length] = newUser;
    l.add(userObj);
    return userObj.toString();
  }

  @DeleteMapping("/api/user/{userId}")
  public void deleteUser(@PathVariable("userId") String id) {
    for(User user : l) {
      if(user.getId().equals(id)) {
        l.remove(user);
      }
    }

  }

  @GetMapping("/api/user/{username}/{firstName}/{lastName}")
  public List findUsersByField(@PathVariable("username") String username,
                               @PathVariable("firstName") String firstName,
                               @PathVariable("lastName") String lastName)
  {
    List<User> newList = new ArrayList<>();
    for(User user : l) {
      if(user.getUsername().equals(username) || username.equals("un=*")) {
        if(user.getFirstName().equals(firstName) || firstName.equals("fn=*")) {
          if(user.getLastName().equals(lastName) || lastName.equals("ln=*")) {
            newList.add(user);
          }
        }
      }
    }
    return newList;
  }


  @PutMapping("/api/user/{userId}")
  public void updateUser(@PathVariable("userId") String id,
                         @RequestBody User user) {
    for(int i = 0; i < l.size(); i++) {
      if (l.get(i).getId().equals(id)) {
        if(user.getUsername().length() != 0) {
          l.get(i).setUsername(user.getUsername());
        }
        if(user.getPassword().length() != 0) {
          l.get(i).setPassword(user.getPassword());
        }
        if(user.getFirstName().length() != 0) {
          l.get(i).setFirstName(user.getFirstName());
        }
        if(user.getLastName().length() != 0) {
          l.get(i).setLastName(user.getLastName());
        }
        l.get(i).setRole(user.getRole());
      }
      else {
        continue;
      }
    }
  }
}


