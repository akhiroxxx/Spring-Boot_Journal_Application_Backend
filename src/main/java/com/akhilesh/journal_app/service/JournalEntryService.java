package com.akhilesh.journal_app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.akhilesh.journal_app.entity.JournalEntry;
import com.akhilesh.journal_app.entity.User;
import com.akhilesh.journal_app.repository.JournalEntryRepo;

@Service
public class JournalEntryService {
  
    @Autowired
    private JournalEntryRepo repo;

    @Autowired
    private UserService userService;

    public List<JournalEntry> getAllJournalEntries(){
      List<JournalEntry> all = repo.findAll();
      return all;
    }


    public void saveEntry(JournalEntry journalEntry, String userName){
      User user = userService.findByUserName(userName);
      journalEntry.setDate(LocalDateTime.now());
      JournalEntry savedEntry = repo.save(journalEntry);
      user.getJournalEntries().add(savedEntry);
      userService.saveUser(user);
    }

    public void saveEntry(JournalEntry journalEntry){
      repo.save(journalEntry);
    }

    public JournalEntry getJournalEntryById(ObjectId id){
      return repo.findById(id).orElse(null);
    }

    public boolean deleteJournalEntryById(ObjectId id, String userName){
      User user = userService.findByUserName(userName);
      Boolean removed = user.getJournalEntries().removeIf(x->x.getId().equals(id));
      if(removed)
      { 
        userService.saveUser(user);
        repo.deleteById(id);
      }
      return removed;
    }

    public ResponseEntity<?> updateJournalEntryById(ObjectId myId, JournalEntry myEntry, String userName) {
      User user = userService.findByUserName(userName);
      List<JournalEntry> collect = user.getJournalEntries().stream().filter(x->x.getId().equals(myId)).collect(Collectors.toList());
      if(!collect.isEmpty())
      {
        JournalEntry j = getJournalEntryById(myId);
        if(j!=null)
        {
          j.setTitle(myEntry.getTitle()!=null&&!myEntry.getTitle().equals("") ? myEntry.getTitle():j.getTitle());
          j.setContent(myEntry.getContent()!=null&&!myEntry.getContent().equals("") ? myEntry.getContent():j.getContent());
          saveEntry(j);
          return new ResponseEntity<>(j, HttpStatus.OK);
        }
      }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public List<JournalEntry> findByUserName(String userName){
      return null;
    }
}
