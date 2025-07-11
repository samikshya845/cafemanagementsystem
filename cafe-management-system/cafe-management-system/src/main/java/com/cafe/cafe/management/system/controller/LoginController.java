package com.cafe.cafe.management.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login"; // Refers to login.html or login.jsp in resources/templates
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard"; // Refers to dashboard.html or dashboard.jsp
    }
}