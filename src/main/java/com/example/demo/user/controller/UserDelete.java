package com.example.demo.user.controller;

import com.example.demo.user.controller.dto.DeleteResponse;
import com.example.demo.user.controller.dto.SearchResponse;
import com.example.demo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/deletUser")
public class UserDelete {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<String> getHelloWorld() {
        return ResponseEntity.ok("Hello from UserController!");
    }

    @PostMapping(produces = "application/json", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<DeleteResponse> search(@RequestBody MultiValueMap<String, String> formData){



        DeleteResponse result;
        boolean response = userService.searchUser(formData.getFirst("azon"));
        if (response==true){
             result = userService.deleteUser(formData);

            return ResponseEntity.ok(result);
        }
        return ResponseEntity.ok(new DeleteResponse(false));
    }
}
