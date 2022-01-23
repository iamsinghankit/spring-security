package com.singhankit.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FooCotroller {

    @GetMapping("/foo")
    public String hello() {
        return "Foo!";
    }
}