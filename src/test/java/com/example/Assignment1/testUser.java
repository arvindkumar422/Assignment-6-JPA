package com.example.Assignment1;
import com.example.Assignment1.model.User;
import com.example.Assignment1.repositories.UserRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testUser {

  @Autowired
  UserRepository ur;

  @Test
  public void findAllUsers() {
    List<User> u = (List<User>) ur.findAll();

    for(User x : u) {
      System.out.println(x.getLastName());
    }
  }
}
