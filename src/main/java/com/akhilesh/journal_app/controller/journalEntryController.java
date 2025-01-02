package com.akhilesh.journal_app.controller;

import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

  @GetMapping("/{userName}")
  public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName){
    User user = userService.findByUserName(userName);
    List<JournalEntry> all =user.getJournalEntries();
    if(all!=null && !all.isEmpty()){
      return new ResponseEntity<>(all, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    // return new ArrayList<>(journalEntries.values());
  }

  @PostMapping("/{userName}")
  public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry journalEntry, @PathVariable String userName){
    try {
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
    JournalEntry journalEntry = journalEntryService.getJournalEntryById(myId);
    if(journalEntry!=null){
      return new ResponseEntity<>(journalEntry, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);

  }

  @DeleteMapping("/id/{userName}/{myId}")
  public ResponseEntity<?> deleteJournalById(@PathVariable ObjectId myId, @PathVariable String userName){
    journalEntryService.deleteJournalEntryById(myId, userName);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    // return journalEntries.remove(myId);
  }

  @PutMapping("/id/{userName}/{myId}")
  public ResponseEntity<?> updateJournalById(@PathVariable ObjectId myId, @RequestBody JournalEntry myEntry, @PathVariable String userName){
    // saves new entry if id matches with existing and returns the new one only
    return journalEntryService.updateJournalEntryById(myId, myEntry, userName);
  }
}
