package com.edigest.journalApp.service;

import com.edigest.journalApp.entity.JournalEntry;
import com.edigest.journalApp.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
@Service
public class JournalEntryService {

    @Autowired
    JournalEntryRepository journalEntryRepository;

    public void saveJournalEntry(JournalEntry journalEntry)
    {
        journalEntryRepository.save(journalEntry);
    }
    public List<JournalEntry> getAllJournalEntries()
    {
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> findById(String Id)
    {
        return journalEntryRepository.findById(Id);
    }

    public void deleteJournalById(String Id)
    {
        journalEntryRepository.deleteById(Id);
    }
}
