package com.akhilesh.journal_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.akhilesh.journal_app.entity.User;
import com.akhilesh.journal_app.repository.UserRepository;

@Component
public class CustomUserDetailsServiceIMPL implements UserDetailsService{

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    
    User user = userRepository.findByUserName(username);
    if(user!=null)
    {
      UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
      .username(user.getUserName())
      .password(user.getPassword())
      .roles(user.getRoles().toArray(new String[0]))
      .build();
      return userDetails;
    }
    throw new UsernameNotFoundException("User with username "+username+" not found!!!");
  }
  
}
