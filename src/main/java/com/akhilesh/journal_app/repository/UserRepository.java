package com.akhilesh.journal_app.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.akhilesh.journal_app.entity.User;


public interface UserRepository extends MongoRepository<User, ObjectId>{
  User findByUserName(String userName);
  void deleteByUserName(String userName);
}
