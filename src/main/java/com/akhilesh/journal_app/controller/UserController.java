package com.akhilesh.journal_app.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akhilesh.journal_app.entity.User;
import com.akhilesh.journal_app.repository.UserRepository;
import com.akhilesh.journal_app.service.UserService;




@RestController
@RequestMapping("/user")
public class UserController {
  
  @Autowired
  private UserService userService;

  @Autowired
  private UserRepository userRepository;



  @PutMapping
  public ResponseEntity<?> updateUser(@RequestBody User user){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();
    User userInDB = userService.findByUserName(username);
    if(userInDB!=null){
      userInDB.setUserName(user.getUserName());
      userInDB.setPassword(user.getPassword());
      userService.saveNewUser(userInDB);
    }
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }


  @DeleteMapping
  public ResponseEntity<?> deleteUserById(){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();
    userRepository.deleteByUserName(username);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }


}
