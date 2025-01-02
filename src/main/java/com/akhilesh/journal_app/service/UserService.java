package com.akhilesh.journal_app.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.akhilesh.journal_app.entity.User;
import com.akhilesh.journal_app.repository.UserRepository;

@Service
public class UserService {
  
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAll(){
      return userRepository.findAll();
    }

    public void saveEntry(User user){
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      user.setRoles(Arrays.asList("USER"));
      userRepository.save(user);
    }

    public Optional<User> findById(ObjectId id){
      return userRepository.findById(id);
    }

    public void deleteUserById(ObjectId id){
      userRepository.deleteById(id);
    }

    public User findByUserName(String userName){
      return userRepository.findByUserName(userName);
    }
}
