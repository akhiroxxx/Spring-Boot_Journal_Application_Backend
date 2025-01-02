package com.akhilesh.journal_app.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
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

import com.akhilesh.journal_app.entity.JournalEntry;
import com.akhilesh.journal_app.entity.User;
import com.akhilesh.journal_app.service.JournalEntryService;
import com.akhilesh.journal_app.service.UserService;


@RestController
@RequestMapping("/journal")
public class journalEntryController {
  
  @Autowired
  private JournalEntryService journalEntryService;

  @Autowired
  private UserService userService;

  @GetMapping
  public ResponseEntity<?> getAllJournalEntriesOfUser(){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userName = authentication.getName();
    User user = userService.findByUserName(userName);
    List<JournalEntry> all =user.getJournalEntries();
    if(all!=null && !all.isEmpty()){
      return new ResponseEntity<>(all, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    // return new ArrayList<>(journalEntries.values());
  }

  @PostMapping
  public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry journalEntry){
    try {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      String userName = authentication.getName();
      journalEntryService.saveEntry(journalEntry, userName);
      return new ResponseEntity<>(journalEntry, HttpStatus.CREATED);
      // journalEntries.put(journalEntry.getId(), journalEntry);
      // return true;
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/id/{myId}")
  public ResponseEntity<JournalEntry> getJournalyById(@PathVariable ObjectId myId){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userName = authentication.getName();
    User user = userService.findByUserName(userName);
    List<JournalEntry> collect = user.getJournalEntries().stream().filter(x->x.getId().equals(myId)).collect(Collectors.toList());
    if(!collect.isEmpty()){
      collect.get(0).getId();
      JournalEntry journalEntry = journalEntryService.getJournalEntryById(myId);
      if(journalEntry!=null){
        return new ResponseEntity<>(journalEntry, HttpStatus.OK);
      }
      
    }
    
    
    return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

  }

  @DeleteMapping("/id/{myId}")
  public ResponseEntity<?> deleteJournalById(@PathVariable ObjectId myId){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userName = authentication.getName();
    if(journalEntryService.deleteJournalEntryById(myId, userName))
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    else
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    // return journalEntries.remove(myId);
  }

  @PutMapping("/id/{myId}")
  public ResponseEntity<?> updateJournalById(@PathVariable ObjectId myId, @RequestBody JournalEntry myEntry){
    // saves new entry if id matches with existing and returns the new one only
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userName = authentication.getName();
    return journalEntryService.updateJournalEntryById(myId, myEntry, userName);
  }
}
