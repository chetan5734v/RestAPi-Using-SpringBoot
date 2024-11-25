package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.services.UserEntryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserEntryServices userEntryServices;

    @GetMapping public List<User> getalluser(){
      return  userEntryServices.getAll();
    }
    @PostMapping public  void createUser(@RequestBody  User user){
        userEntryServices.safeEntry(user);
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable String username){
         User userindb= userEntryServices.findbyusername(username);
         if(userindb!=null){
             userindb.setUserName(user.getUserName());
             userindb.setPassword(user.getPassword());
             userEntryServices.safeEntry(userindb);
         }
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
