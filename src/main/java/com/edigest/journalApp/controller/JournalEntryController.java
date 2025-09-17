package com.edigest.journalApp.controller;

import com.edigest.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    private Map<Long,JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll()
    {
        return new ArrayList<>(journalEntries.values());
    }
    @PostMapping
    public boolean createJournalEntry(@RequestBody JournalEntry entry)
    {
        journalEntries.put(entry.getId(),entry);
        return true;
    }
    @GetMapping("/id/{myId}")
    public JournalEntry findById(@PathVariable Long myId){
        return journalEntries.get(myId);
    }

    @PutMapping("id/{id}")
    public JournalEntry updateJournalById(@RequestBody JournalEntry journalEntry, @PathVariable Long id)
    {
        journalEntries.put(id,journalEntry);
        return journalEntries.get(id);
    }

    @DeleteMapping("id/{myId}")
    public boolean deleteJournalById(@PathVariable Long myId)
    {
        journalEntries.remove(myId);
        return true;
    }
}
