package com.akhilesh.journal_app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.akhilesh.journal_app.entity.JournalEntry;
import java.util.Optional;


public interface JournalEntryRepo extends MongoRepository<JournalEntry, String>{
  Optional<JournalEntry> getJournalEntryById(String id);
}
