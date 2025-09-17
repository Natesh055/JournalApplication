package com.edigest.journalApp.controller;

import com.edigest.journalApp.entity.JournalEntry;
import com.edigest.journalApp.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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
    public JournalEntry createJournalEntry(@RequestBody JournalEntry entry)
    {
        entry.setDate(LocalDateTime.now());
        journalEntryService.saveJournalEntry(entry);
        return entry;
    }
    @GetMapping("/id/{myId}")
    public JournalEntry findById(@PathVariable String myId){
        JournalEntry byId = journalEntryService.findById(myId);
        if(byId == null)
            System.out.println("No entries were found");
        return byId;
    }

    @PutMapping("id/{id}")
    public JournalEntry updateJournalById(@RequestBody JournalEntry newEntry, @PathVariable String  id)
    {
        JournalEntry oldEntry = journalEntryService.findById(id);
        if(oldEntry != null)
        {
            oldEntry.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")?
                    newEntry.getTitle():oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("") ?
                    newEntry.getContent(): oldEntry.getContent());
            oldEntry.setDate(LocalDateTime.now());

        }
        journalEntryService.saveJournalEntry(oldEntry);
        return oldEntry;
    }

    @DeleteMapping("id/{myId}")
    public void deleteJournalById(@PathVariable String myId)
    {
        journalEntryService.deleteJournalById(myId);
    }
}
