package com.edigest.journalApp.service;

import com.edigest.journalApp.entity.JournalEntry;
import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveJournalEntry(JournalEntry journalEntry, String userName)
    {
        User user = userService.findUserByUserName(userName);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntryList().add(saved);
        userService.updateUser(user);
    }
    public void updateJournalEntry(JournalEntry journalEntry)
    {
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepository.save(journalEntry);
    }
    public List<JournalEntry> getAllJournalEntries()
    {
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> findById(ObjectId Id)
    {
        return journalEntryRepository.findById(Id);
    }

    public void deleteJournalById(ObjectId id, String userName)
    {
        User user = userService.findUserByUserName(userName);
        user.getJournalEntryList().removeIf(x->x.getId().equals(id));
        userService.updateUser(user);
        journalEntryRepository.deleteById(id);
    }
}
