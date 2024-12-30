package com.akhilesh.journal_app.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.akhilesh.journal_app.entity.JournalEntry;


public interface JournalEntryRepo extends MongoRepository<JournalEntry, ObjectId>{
}
