package com.example.springboot.controller;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.service.loginService;

@RestController
@RequestMapping(path="/login")
public class loginController {
    @Autowired
    loginService service;
    @PutMapping("/{username}/{pwd}") 
    String key(@PathVariable String username,@PathVariable String pwd) throws NullPointerException
    {  //throw new NameNotFoundException();
        //throw new NullPointerException();
        return service.generateToken(username,pwd);
    }
      @GetMapping("/verify/{username}")
    public ResponseEntity<String> tokenValidation(@RequestHeader("Authorization") String authorizationHeader,@PathVariable String username) throws RuntimeException{
        //throw new RuntimeException();
        String token=service.extractToken(authorizationHeader);
        if (service.validateToken(token,username)) {
            return ResponseEntity.ok("Valid token");
        } 
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
    }

}


