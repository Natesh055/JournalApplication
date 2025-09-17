package com.edigest.journalApp.entity;

import lombok.Data;

@Data
public class JournalEntry {
    private Long id;
    private String title;
    private String content;
}
