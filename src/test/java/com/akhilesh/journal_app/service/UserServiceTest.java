package com.akhilesh.journal_app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.akhilesh.journal_app.repository.UserRepository;

@SpringBootTest
public class UserServiceTest {

  @Autowired
  private UserRepository userRepository;

  
  @ParameterizedTest
  @CsvSource({
    "ram",
    "ak",
    "AKL",
    "haha"
  })
  public void findByUserNameTest(String username){
    assertNotNull(userRepository.findByUserName(username), "message apppears when "+username+" failed");
  }

  @ParameterizedTest
  @CsvSource({
    "1,1,2",
    "1,2,3",
    "5,6,11"
  })
  public void add(int a, int b, int expected){
    assertEquals(expected, a+b);
  }
  
}
