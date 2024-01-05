package com.example.springboot.filter;

import java.io.IOException;

import org.springframework.web.client.RestClientException;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthFilter extends OncePerRequestFilter {
  String sceretkey="*U(8hj908ns98daniasudfniawur97q2e7r2934892rnu213rn09217349782190348y12";
     public boolean validateToken(String token)
     {try{
        Jwts.parser().setSigningKey(sceretkey).parseClaimsJws(token);
        return true; 
    }
     catch(Exception e)
     {
        return false;
     }
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                String token=request.getHeader("Authorization").substring(7);
                if(!validateToken(token))
                {
                    throw new RestClientException("Token invalid");
                }
                filterChain.doFilter(request,response);
    }
    
    
}
