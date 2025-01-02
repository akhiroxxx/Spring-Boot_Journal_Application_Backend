package com.akhilesh.journal_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akhilesh.journal_app.entity.User;
import com.akhilesh.journal_app.service.UserService;

@RestController
@RequestMapping("/public")
public class publicController {

  @Autowired
  private UserService userService;
  
  @GetMapping("/health-check")
  public String health(){
    return "ok";
  }

  
  @PostMapping("/create-user")
  public void createUser(@RequestBody User user){
    userService.saveEntry(user);
  }
}
