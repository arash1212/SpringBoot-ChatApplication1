package com.example.springbootchatapplication1.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String goToIndex() {
        return "index";
    }

    @GetMapping("/pub/user/register")
    public String goToUserRegister() {
        return "/pub/user/register";
    }

    @GetMapping("/pub/user/login")
    public String goToUserLogin() {
        return "/pub/user/login";
    }

    @GetMapping("/pub/user/profile")
    public String goToUserProfile() {
        return "/pub/user/profile";
    }
}
