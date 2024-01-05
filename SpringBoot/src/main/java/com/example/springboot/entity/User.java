package com.example.springboot.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    String email;
    String username;
    String pwd;
    String role;

}
