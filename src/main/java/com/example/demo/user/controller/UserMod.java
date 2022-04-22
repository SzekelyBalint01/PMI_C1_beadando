package com.example.demo.user.controller;

import com.example.demo.user.controller.dto.SearchResponse;
import com.example.demo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userMod")
public class UserMod {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<String> getHelloWorld() {
        return ResponseEntity.ok("Hello from UserMod!");
    }

    @PostMapping(produces = "application/json", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<SearchResponse> mod(@RequestBody MultiValueMap<String, String> formData){


        String azon = formData.getFirst("azon");
        SearchResponse result = userService.actualleData(formData.getFirst("azon"));

          userService.modifyData( formData);


        SearchResponse data2 = userService.actualleData(result.getAdoazon());

        return ResponseEntity.ok(data2);


    }
}
