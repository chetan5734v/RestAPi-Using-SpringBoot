package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.journalEntry;
import net.engineeringdigest.journalApp.services.journalentryServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")

public class JournalEntryControllerv2 {
    @Autowired
    private  journalentryServices journalentryServices;
    @GetMapping
    public List<journalEntry> getall(){
        return journalentryServices.getAll();

    }
    @PostMapping
    public  Boolean createEntry(@RequestBody journalEntry myEntry){
        myEntry.setDate(LocalDateTime.now() );
        journalentryServices.safeEntry(myEntry);
        return true;

    }
    @GetMapping("id/{myid}")
    public journalEntry getbyid(@PathVariable ObjectId myid){
        return  journalentryServices.findbyid(myid).orElse(null);

    }
    @DeleteMapping("id/{myid}")
    public Boolean deletebyid(@PathVariable ObjectId myid){
        journalentryServices.deletebyId(myid);
        return true;
    }
    @PutMapping("id/{id}")
    public journalEntry updatebyid(@PathVariable ObjectId id, @RequestBody journalEntry myentry){
        journalEntry old= journalentryServices.findbyid(id).orElse(null);
        if(old!=null){
            old.setTitle(myentry.getTitle()!=null && !myentry.getTitle().isEmpty() ? myentry.getTitle(): old.getTitle());
            old.setContent(myentry.getContent()!=null && !myentry.getContent().isEmpty() ? myentry.getContent() : old.getContent());
        }
        journalentryServices.safeEntry(old);
        return old;
    }
}
