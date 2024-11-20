package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.journalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    private Map<Long,journalEntry> journalEntries=new HashMap();
  @GetMapping
  public List<journalEntry>getall(){
      return new ArrayList<>(journalEntries.values());

  }
  @PostMapping
  public  Boolean createEntry(@RequestBody journalEntry myEntry){
      journalEntries.put(myEntry.getId(),myEntry);
      return true;

  }
  @GetMapping("id/{myid}")
  public journalEntry getbyid(@PathVariable Long myid){
      return journalEntries.get(myid);

  }
    @DeleteMapping("id/{myid}")
    public journalEntry deletebyid(@PathVariable Long myid){
        return journalEntries.remove(myid);

    }
     @PutMapping("id/{id}")
     public journalEntry updatebyid(@PathVariable Long id, @RequestBody journalEntry myentry){
      return  journalEntries.put(id,myentry);
    }
}