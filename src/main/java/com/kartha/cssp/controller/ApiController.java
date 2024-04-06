package com.kartha.cssp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api")
public class ApiController {

    // generate sample response
    @GetMapping(path = "/sample", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sample() {
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }
    
}
