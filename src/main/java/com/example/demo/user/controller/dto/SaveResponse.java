package com.example.demo.user.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
@AllArgsConstructor
public class SaveResponse {
   private boolean saved = false;

    public SaveResponse() {

    }
}
