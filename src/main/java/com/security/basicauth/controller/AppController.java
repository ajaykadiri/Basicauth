package com.security.basicauth.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class AppController {
    @GetMapping("/message")
    public String greeting(HttpServletRequest request){
        String uname = (String) request.getAttribute("username");
        return "Welcome " +uname+"!";
    }
}
