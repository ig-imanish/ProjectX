package com.projectx.projectx.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {

    @GetMapping("/**")
    public String handleNotFound() {
        return "Page not found";
    }

}
