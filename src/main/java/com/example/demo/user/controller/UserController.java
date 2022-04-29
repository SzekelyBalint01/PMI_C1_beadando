package com.example.demo.user.controller;

import com.example.demo.user.controller.dto.SaveResponse;
import com.example.demo.user.controller.dto.SearchResponse;
import com.example.demo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<String> getHelloWorld() {
        return ResponseEntity.ok("Hello from UserController!");
    }

    @PostMapping(produces = "application/json", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<SearchResponse> search(@RequestBody MultiValueMap<String, String> formData){

        String azon = formData.getFirst("azon");


        boolean response = userService.searchUser(azon);
        if (response==true){
           SearchResponse result = userService.actualleData(azon);

            return ResponseEntity.ok(result);
        }
        return ResponseEntity.ok(null);
    }
}
