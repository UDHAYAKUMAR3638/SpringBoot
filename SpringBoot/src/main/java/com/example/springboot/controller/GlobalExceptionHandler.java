package com.example.springboot.controller;
import javax.naming.NameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value=NameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNotFoundException(NameNotFoundException ex) {
        return new ResponseEntity<>("Resource not found: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

     @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>("failed due to RunTimeException", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value=NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException ex)
    {
        return new ResponseEntity<String>("returns null from call",HttpStatus.BAD_REQUEST);
    }

}
