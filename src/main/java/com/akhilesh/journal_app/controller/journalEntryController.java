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
import com.akhilesh.journal_app.service.JournalEntryService;


@RestController
@RequestMapping("/journal")
public class journalEntryController {
  
  @Autowired
  private JournalEntryService journalEntryService;

  @GetMapping
  public ResponseEntity<?> getAll(){
    List<JournalEntry> all = journalEntryService.getAllJournalEntries();
    if(all!=null && !all.isEmpty()){
      return new ResponseEntity<>(all, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    // return new ArrayList<>(journalEntries.values());
  }

  @PostMapping
  public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry journalEntry){
    try {
      journalEntryService.saveEntry(journalEntry);
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

  @DeleteMapping("/id/{myId}")
  public ResponseEntity<?> deleteJournalById(@PathVariable ObjectId myId){
    journalEntryService.deleteJournalEntryById(myId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    // return journalEntries.remove(myId);
  }

  @PutMapping("/id/{myId}")
  public ResponseEntity<?> updateJournalById(@PathVariable ObjectId myId, @RequestBody JournalEntry myEntry){
    // saves new entry if id matches with existing and returns the new one only
    return journalEntryService.updateJournalEntryById(myId, myEntry);
  }
}
