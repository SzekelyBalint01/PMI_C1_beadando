package com.example.demo.user.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
@AllArgsConstructor
public class SearchResponse {
    private String adoazon;
    private String telephely;
    private String szervezet;
    private String belepeskelte;
    private String kilepeskelte;
    private String bruttoalapber;
    private String cegneve;
    private String cegadoszama;
    private String cegszekhelye;
    private String ledolgnap;
    private String munkaszunnap;
    private String torzsber;
    private String fname;
    private String lname;

    public SearchResponse() {

    }
}
