package com.divyansh.studentmanagementsystem.project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class adminController {

    @RequestMapping("/dashboard")
    public String adminDashboard() {
        return "Welcome Admin";
    }

}
