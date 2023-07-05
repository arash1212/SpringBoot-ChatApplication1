package com.example.springbootchatapplication1.controller;

import com.example.springbootchatapplication1.utils.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/pub")
public class TestController {

    @Autowired
    private FileService fileService;

//    @GetMapping(path = "/file-service/test")
//    public void createFile() {
//        this.fileService.storeFile();
//    }

}
