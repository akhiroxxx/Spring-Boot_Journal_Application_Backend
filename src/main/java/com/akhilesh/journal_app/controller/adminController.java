package com.akhilesh.journal_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akhilesh.journal_app.entity.User;
import com.akhilesh.journal_app.service.UserService;

@RestController
@RequestMapping("/admin")
public class adminController {

  @Autowired
  private UserService userService;


  @GetMapping("/all-users")
  public ResponseEntity<?> getAllUsers(){
    List<User> all = userService.getAll();
    if(all!=null && !all.isEmpty()){
      return new ResponseEntity<>(all, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
  
}
