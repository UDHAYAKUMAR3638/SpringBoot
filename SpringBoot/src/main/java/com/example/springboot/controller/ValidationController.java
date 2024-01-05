package com.example.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.service.loginService;

import jakarta.servlet.http.HttpServletRequest;
@RestController
@RequestMapping(path="/token")
public class ValidationController {
     @Autowired
    loginService service;
    @GetMapping("/valid/{username}")
    public ResponseEntity<?> Hello(HttpServletRequest request, String username)
    {   String token=request.getHeader("Authorization").substring(7);
        if(service.validateToken(token,username))
        return new ResponseEntity<>("Authorized user",HttpStatus.OK);
        return new ResponseEntity<>("Not Authorized user",HttpStatus.NOT_ACCEPTABLE);
    }
    
    @GetMapping("/greet")
    public ResponseEntity<?> greet(@RequestBody String name)
    {
        return new ResponseEntity<>("greetings "+name,HttpStatus.OK);
    }
}
