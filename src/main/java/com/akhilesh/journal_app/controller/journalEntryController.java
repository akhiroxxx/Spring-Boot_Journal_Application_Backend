package com.akhilesh.journal_app.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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
  public List<JournalEntry> getAll(){
    return journalEntryService.getAllJournalEntries();
    // return new ArrayList<>(journalEntries.values());
  }

  @PostMapping
  public boolean createEntry(@RequestBody JournalEntry journalEntry){
    journalEntryService.saveEntry(journalEntry);
    return true;
    // journalEntries.put(journalEntry.getId(), journalEntry);
    // return true;
  }

  @GetMapping("/id/{myId}")
  public JournalEntry getJournalyById(@PathVariable ObjectId myId){
    JournalEntry j = journalEntryService.getJournalEntryById(myId);
    if(j!=null)
    return j;
    else
    return null;
  }

  @DeleteMapping("/id/{myId}")
  public JournalEntry deleteJournalById(@PathVariable ObjectId myId){
    return journalEntryService.deleteJournalEntryById(myId);
    // return journalEntries.remove(myId);
  }

  @PutMapping("/id/{myId}")
  public JournalEntry updateJournalById(@PathVariable ObjectId myId, @RequestBody JournalEntry myEntry){
    // saves new entry if id matches with existing and returns the new one only
    return journalEntryService.updateJournalEntryById(myId, myEntry);
  }
}
