package com.learningAayush.dailyQuotes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class HealthCheck {
    @GetMapping("/ok")
    public String healthCheck(){
        return "Health Check: OK";
    }
}
