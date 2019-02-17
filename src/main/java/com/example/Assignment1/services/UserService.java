package com.example.Assignment1.services;

import com.example.Assignment1.model.Role;
import com.example.Assignment1.model.User;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;


@RestController
@CrossOrigin(origins = "https://webservices-5.herokuapp.com")

public class UserService {



  User alice = new User("123", "abc", "ppp",
          "def", "ghi", Role.FACULTY);
  User bob = new User("456", "bob", "qqq",
          "jkl", "mno", Role.STUDENT);
  User carl = new User("789", "carl", "rrr",
          "pqr", "stu", Role.ADMIN);

  List<User> users = new ArrayList(){{
    add(alice);
    add(bob);
    add(carl);
  }};



  @GetMapping("/api/user")
  public List findAllUsers() {
    return users;
  }

  @GetMapping("/api/user/{userId}")
  public User findUserById(@PathVariable("userId") String id) {
    for(User user : users) {
      if (id.equals(user.getId())) {
        return user;
      }
    }
    return null;
  }

  @CrossOrigin(allowCredentials = "true")
  @PostMapping("/api/register")
  public User register(@RequestBody User userObj, HttpSession session) {
    for (User user:users){
      if (user.getUsername().equals(userObj.getUsername())){
        return new User("0",null,null,null,null,null);
      }
    }
    session.setAttribute("currentUser",userObj);
    users.add(userObj);
    return userObj;
  }

  @PostMapping("api/profile")
  public User profile(HttpSession session) {
    User prof = (User) session.getAttribute("currentUser");
    return prof;
  }

  @CrossOrigin(allowCredentials = "true")
  @PostMapping("api/login")
  public User login(@RequestBody User prof, HttpSession session) {
    for(User user:users) {
      if(user.getUsername().equals(prof.getUsername()) &&
              user.getPassword().equals(prof.getPassword())) {
        session.setAttribute("currentUser", user);
        return prof;
      }
    }
    return new User("0",null,null,null,null,null);
  }

  @PostMapping("/create")
  public String createUser(@RequestBody User userObj) {
//    User newUser = new User(110, username, "asd", "asd");
//    users[users.length] = newUser;
    users.add(userObj);
    return userObj.toString();
  }

  @DeleteMapping("/api/user/{userId}")
  public void deleteUser(@PathVariable("userId") String id) {
    for(User user : users) {
      if(user.getId().equals(id)) {
        users.remove(user);
      }
    }
  }

  @GetMapping("/api/user/{username}/{firstName}/{lastName}/{role}")
  public List findUsersByField(@PathVariable("username") String username,
                               @PathVariable("firstName") String firstName,
                               @PathVariable("lastName") String lastName,
                               @PathVariable("role") Role role)
  {
    List<User> newList = new ArrayList<>();
    for(User user : users) {
      if(user.getUsername().equals(username) || username.equals("un=*")) {
        if(user.getFirstName().equals(firstName) || firstName.equals("fn=*")) {
          if(user.getLastName().equals(lastName) || lastName.equals("ln=*")) {
            if(user.getRole().equals(role) || role.equals("rl=*")) {
              newList.add(user);
            }
          }
        }
      }
    }
    return newList;
  }


  @PutMapping("/api/user/{userId}")
  public void updateUser(@PathVariable("userId") String id,
                         @RequestBody User user) {
    for(int i = 0; i < users.size(); i++) {
      if (users.get(i).getId().equals(id)) {
        if(user.getUsername().length() != 0) {
          users.get(i).setUsername(user.getUsername());
        }
        if(user.getPassword().length() != 0) {
          users.get(i).setPassword(user.getPassword());
        }
        if(user.getFirstName().length() != 0) {
          users.get(i).setFirstName(user.getFirstName());
        }
        if(user.getLastName().length() != 0) {
          users.get(i).setLastName(user.getLastName());
        }
        users.get(i).setRole(user.getRole());
      }
      else {
        continue;
      }
    }
  }

  @PostMapping("/api/logout")
  public void logout
          (HttpSession session) {
    session.invalidate();
  }

}


