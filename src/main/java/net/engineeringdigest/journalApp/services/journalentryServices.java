package net.engineeringdigest.journalApp.services;

import net.engineeringdigest.journalApp.entity.journalEntry;
import net.engineeringdigest.journalApp.repository.journalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class journalentryServices {
   @Autowired
    private journalEntryRepository journalEntryRepository;

    public void safeEntry(journalEntry journalEntry){
        journalEntryRepository.save(journalEntry);

    }
    public List<journalEntry> getAll(){
       return  journalEntryRepository.findAll();

    }
    public Optional<journalEntry> findbyid(ObjectId id){
        return  journalEntryRepository.findById(id);
    }
    public  void deletebyId(ObjectId id){
        journalEntryRepository.deleteById(id);
    }

}
