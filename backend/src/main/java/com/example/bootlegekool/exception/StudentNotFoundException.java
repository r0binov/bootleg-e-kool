package com.example.bootlegekool.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentNotFoundException extends RuntimeException{

    public StudentNotFoundException(String msg) {
        super(msg);
    }
}
