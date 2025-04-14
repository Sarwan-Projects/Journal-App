package com.example.demo.service;

import com.example.demo.entity.JournalEntry;
import com.example.demo.entity.User;
import com.example.demo.repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class JournalEntryService
{
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry my, String username)
    {
        try
        {
            User byuser = userService.findByUserName(username);
            my.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(my);
            byuser.getJournalEntries().add(saved);
            userService.saveUser(byuser);
        } catch (Exception e) {
            throw new RuntimeException("An Error Occured while saving the entry : ",e);
        }
    }

    public void saveEntry(JournalEntry my)
    {
        try
        {
            journalEntryRepository.save(my);
        } catch (Exception e) {
            log.error("Exception : ",e);
        }
    }

    public List<JournalEntry> getAll()
    {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id)
    {
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String username)
    {
        boolean removed = false;
        try
        {
            User byuser = userService.findByUserName(username);
            removed = byuser.getJournalEntries().removeIf(x->x.getId().equals(id));
            if(removed)
            {
                userService.saveUser(byuser);
                journalEntryRepository.deleteById(id);
            }
        }
        catch (Exception ex)
        {
            log.error("Error : ",ex);
            throw new RuntimeException("An Error Occurred while deleting the entry",ex);
        }
        return removed;
    }
}
