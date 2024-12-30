package com.akhilesh.journal_app.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "journal_entries")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JournalEntry {
  
  @Id
  private String id;

  private String title;

  private String content;

  private LocalDateTime date;
}
