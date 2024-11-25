package net.engineeringdigest.journalApp.services;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserEntryServices {
   @Autowired
    private UserEntryRepository userEntryRepository;

    public void safeEntry(User user){
        userEntryRepository.save(user);

    }
    public List<User> getAll(){
       return  userEntryRepository.findAll();

    }
    public Optional<User> findbyid(ObjectId id){
        return  userEntryRepository.findById(id);
    }
    public  void deletebyId(ObjectId id){
        userEntryRepository.deleteById(id);
    }
    public  User findbyusername(String UserName){
        return userEntryRepository.findByUserName(UserName);
    }

}
