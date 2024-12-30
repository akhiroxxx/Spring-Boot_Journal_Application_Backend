package com.akhilesh.journal_app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akhilesh.journal_app.entity.JournalEntry;
import com.akhilesh.journal_app.repository.JournalEntryRepo;

@Service
public class JournalEntryService {
  
    @Autowired
    private JournalEntryRepo repo;

    public List<JournalEntry> getAllJournalEntries(){
      return repo.findAll();
    }

    public void saveEntry(JournalEntry journalEntry){
      journalEntry.setDate(LocalDateTime.now());
      repo.save(journalEntry);
    }

    public Optional<JournalEntry> getJournalEntryById(String id){
      return repo.getJournalEntryById(id);
    }

    public JournalEntry deleteJournalEntryById(String id){
      Optional<JournalEntry> j = getJournalEntryById(id);
      if(j.isPresent()){
        repo.delete(j.get());
        return j.get();
      }
      else
      return null;
    }

    public JournalEntry updateJournalEntryById(String myId, JournalEntry myEntry) {
      if(!myEntry.getId().equals(myId)){
        System.out.println(myEntry.getId()==myId);
        System.out.println(myId+" "+myId.getClass().getName());
        System.out.println(myEntry.getId()+" "+myEntry.getId().getClass().getName());
        return null;
      }
      Optional<JournalEntry> j = getJournalEntryById(myId);
      if(!j.isPresent()){
        saveEntry(myEntry);
        return null;
      }
      else{
        JournalEntry to_be_returned = deleteJournalEntryById(myId);
        saveEntry(myEntry);
        return to_be_returned;
      }
    }
}
