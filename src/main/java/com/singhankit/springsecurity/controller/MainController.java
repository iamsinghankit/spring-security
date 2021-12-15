package com.singhankit.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author _singhankit
 */
@Controller
public class MainController {

    @GetMapping
    public String main(){
        return "main";
    }

    @PostMapping("/change")
    public String post(){
        System.out.println("hacked");
        return "main";
    }
}
