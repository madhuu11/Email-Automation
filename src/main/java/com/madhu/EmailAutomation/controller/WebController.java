package com.madhu.EmailAutomation.controller;

//generate rest controller email template controller class with request mapping /dashboard
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class WebController {

    @GetMapping
    public String home() {
        return "index";
    }

}