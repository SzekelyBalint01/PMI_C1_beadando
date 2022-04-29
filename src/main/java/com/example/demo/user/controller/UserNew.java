package com.example.demo.user.controller;

import com.example.demo.user.controller.dto.SaveResponse;
import com.example.demo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

@RestController
@RequestMapping("/newUser")
public class UserNew {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<String> getHelloWorld() {
        return ResponseEntity.ok("Hello from UserMod!");
    }

    @PostMapping(produces = "application/json", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<SaveResponse> newUser(@RequestBody MultiValueMap<String, String> formData) throws IOException, ParserConfigurationException, TransformerException, SAXException {

          SaveResponse result = userService.saveNewUser(formData);
        return ResponseEntity.ok(result);
    }
}