package com.singhankit.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author _singhankit
 */
@Controller
//    @CrossOrigin("http://localhost:8080") use it for just POC
public class MainController {


    @GetMapping("/")
    public String main() {
        return "main";
    }

    //cors doesn't block this call to be invoked it will be invoked so if any changes in the database it will be done
    // what cors will be blocking is the response getting back to the client(depends on browser basically)
    // In preflight request the method will not be called, but in non-preflight request the method will be called
    @PostMapping("/test")
    @ResponseBody
//    @CrossOrigin("*") use it for just POC
    public String test(){
        System.out.println(":(");// it will be printed
      return "TEST!";
    }
}
