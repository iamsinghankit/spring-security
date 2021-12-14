package com.singhankit.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author _singhankit
 */
@RestController
public class FooController {

    @GetMapping("/foo")
    public String foo() {
        return "foo!";
    }
}
