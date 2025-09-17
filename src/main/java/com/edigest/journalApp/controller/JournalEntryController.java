package com.edigest.journalApp.controller;

import com.edigest.journalApp.entity.JournalEntry;
import com.edigest.journalApp.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@Component
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll()
    {
        return journalEntryService.getAllJournalEntries();
    }
    @PostMapping
    public ResponseEntity<JournalEntry> createJournalEntry(@RequestBody JournalEntry entry)
    {
        try {
            entry.setDate(LocalDateTime.now());
            journalEntryService.saveJournalEntry(entry);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception exception)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> findById(@PathVariable String myId){
        Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<JournalEntry> updateJournalById(@RequestBody JournalEntry newEntry, @PathVariable String  id)
    {
        try {
            Optional<JournalEntry> journalEntry = journalEntryService.findById(id);
            JournalEntry oldEntry = journalEntry.get();
            if (journalEntry.isPresent()) {
                oldEntry.setTitle(newEntry.getTitle() != null && newEntry.getTitle().equals("") ?
                        newEntry.getTitle() : oldEntry.getTitle());
                oldEntry.setContent(newEntry.getContent() != null && newEntry.getContent().equals("") ?
                        newEntry.getContent() : oldEntry.getContent());
                oldEntry.setDate(LocalDateTime.now());
            }
            journalEntryService.saveJournalEntry(oldEntry);
            return new ResponseEntity<>(oldEntry,HttpStatus.OK);
        }
        catch (Exception exception)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteJournalById(@PathVariable String myId)
    {
        Optional<JournalEntry> entryToDelete = journalEntryService.findById(myId);
        if(entryToDelete.isPresent())
        {
            journalEntryService.deleteJournalById(myId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
