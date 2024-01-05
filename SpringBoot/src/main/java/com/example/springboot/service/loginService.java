package com.example.springboot.service;
import io.jsonwebtoken.*;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.Data;

@Service
@Data
public class loginService {
    public String getToken(String username)
    {
        Map<String,Object> map=new HashMap<>();
        String role="admin";
        map.put("Role",role);
        Claims claim=Jwts.claims().setSubject(username);
        claim.putAll(map);
        String token=Jwts.builder().setClaims(claim).signWith(SignatureAlgorithm.HS256,"*U(8hj908ns98daniasudfniawur97q2e7r2934892rnu213rn09217349782190348y12").compact();
        Claims claims=Jwts.parser().setSigningKey("*U(8hj908ns98daniasudfniawur97q2e7r2934892rnu213rn09217349782190348y12").parseClaimsJws(token).getBody();
        String roles=claims.get("Role").toString();
        System.out.println(roles);
        return token;
    }
    public String generateToken(String username,String pwd)
    {
        Claims claims=Jwts.claims().setSubject(username);
        System.out.println("Claims :"+claims);
        String token=Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256,"*U(8hj908ns98daniasudfniawur97q2e7r2934892rnu213rn09217349782190348y12").compact();
        System.out.println("Token: "+token);
        return token;
    }
    public String extractToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7); 
        }
        return null;
    }

    public boolean validateToken(String token,String username) {
        System.out.println( "token "+token);
        String tokengot=generateToken(username,"");
        if(tokengot.equals(token))
            return true;
        return false;
    }
}
