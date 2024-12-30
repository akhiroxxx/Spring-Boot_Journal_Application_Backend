package com.akhilesh.journal_app.service;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;
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

    public JournalEntry getJournalEntryById(ObjectId id){
      return repo.findById(id).orElse(null);
    }

    public JournalEntry deleteJournalEntryById(ObjectId id){
      JournalEntry j = getJournalEntryById(id);
      if(j!=null){
        repo.deleteById(j.getId());
        return j;
      }
      else
      return null;
    }

    public JournalEntry updateJournalEntryById(ObjectId myId, JournalEntry myEntry) {
      JournalEntry j = getJournalEntryById(myId);
      if(j!=null)
      {
        j.setTitle(myEntry.getTitle()!=null&&!myEntry.getTitle().equals("") ? myEntry.getTitle():j.getTitle());
        j.setContent(myEntry.getContent()!=null&&!myEntry.getContent().equals("") ? myEntry.getContent():j.getContent());
      }
      saveEntry(j);
      return j;
    }
}
