package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.entity.journalEntry;
import net.engineeringdigest.journalApp.services.UserEntryServices;
import net.engineeringdigest.journalApp.services.journalentryServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")

public class JournalEntryControllerv2 {
    @Autowired
    private  journalentryServices journalentryServices;
    @Autowired
    private UserEntryServices userEntryServices;
    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName){

        User user= userEntryServices.findbyusername(userName);
        List<journalEntry> all= user.getJournalEntries();
        if(all!=null && !all.isEmpty()){
            return  new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<journalEntry>(HttpStatus.BAD_REQUEST);

    }
    @PostMapping("{userName}")
    public  ResponseEntity<journalEntry> createEntry(@RequestBody journalEntry myEntry,@PathVariable String userName){

        try {
            myEntry.setDate(LocalDateTime.now() );
            journalentryServices.safeEntry(myEntry,userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }
    @GetMapping("id/{myid}")
    public ResponseEntity<journalEntry> getbyid(@PathVariable ObjectId myid){
         Optional<journalEntry> journalEntry= journalentryServices.findbyid(myid);
         if(journalEntry.isPresent()){
             return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
         }
         else{
             return new ResponseEntity<>( HttpStatus.NOT_FOUND);
         }

    }
    @DeleteMapping("id/{myid}")
    public ResponseEntity<?> deletebyid(@PathVariable ObjectId myid){
        journalentryServices.deletebyId(myid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("id/{id}")
    public ResponseEntity<?> updatebyid(@PathVariable ObjectId id, @RequestBody journalEntry myentry){
        journalEntry old= journalentryServices.findbyid(id).orElse(null);
//        if(old!=null){
//            old.setTitle(myentry.getTitle()!=null && !myentry.getTitle().isEmpty() ? myentry.getTitle(): old.getTitle());
//            old.setContent(myentry.getContent()!=null && !myentry.getContent().isEmpty() ? myentry.getContent() : old.getContent());
//            journalentryServices.safeEntry(old);
//              return new ResponseEntity<>( HttpStatus.OK);
//
//        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);

    }
}
