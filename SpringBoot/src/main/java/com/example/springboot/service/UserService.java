package com.example.springboot.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springboot.entity.User;
import com.example.springboot.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    UserRepository repo;
     public Boolean saveUser(User u)
     {  String Id=u.getEmail();
        if(repo.findById(Id)==null)
         {repo.save(u);
        return true;
         }
         return false;
     }

    public List<User> getAllUser()
    {
        return repo.findAll();
    }

    public User getUserByEmail(String Id)
    { 
       try{ 
        if(repo.existsById(Id))
        return repo.findById(Id).get();
        throw new NameNotFoundException();
        }
        catch(Exception e)
        { return null;
        }
    }

    public Boolean deleteUserByEmail(String Id)
    {  boolean idExists=false;
        try{
            if(repo.existsById(Id))
        {
            repo.deleteById(Id);
            idExists=true;
        }
      return idExists;
        }
        catch(Exception e)
        {
            throw new NoSuchElementException();
        }
    }

    public User updateUser(String email,User u) throws Exception
    {   try{
        Optional<User> f1=repo.findById(email);
        User f2=null;
        if(f1.isPresent()){
         f2=f1.get();
         f2.setUsername(u.getUsername());
         f2.setEmail(u.getEmail());
         f2.setPwd(u.getPwd());
         f2.setRole(u.getRole());
         repo.save(f2);
        }
        return f2;
    }
    catch(Exception e)
    {
        throw new Exception();
    }
    }

}
