package com.example.springbootchatapplication1.controller.pub;

import com.example.springbootchatapplication1.utils.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(path = "/pub/files/")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file, @RequestParam(name = "subDirectories") String subDirectories) {
        return new ResponseEntity<>(fileService.storeFile(file, subDirectories), HttpStatus.CREATED);
    }
}
