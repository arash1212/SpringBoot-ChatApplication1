package com.example.springbootchatapplication1.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.util.UUID;

@Component
public class FileService {
    private final String rootLocation;

    public FileService() {
        //todo pub , adm o ...
        rootLocation = File.separator + "uploadedFiles";
    }

    public String createDirectoriesIfNotExist(String subDirectories) {
        String classPath;
        try {
            classPath = ResourceUtils.getFile("classpath:static").getAbsolutePath() + File.separator;
            File file = new File(classPath + rootLocation + File.separator + subDirectories + File.separator);
            file.mkdirs();
            return file.getPath();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String storeFile(MultipartFile file, String subDirectories) {
        String directory = createDirectoriesIfNotExist(subDirectories);
        try {
            String extension = file.getOriginalFilename().split("\\.")[1];
            String fileName = UUID.randomUUID() + "." + extension;
            Path path = Path.of(directory + File.separator + fileName);
            file.transferTo(path);
            return rootLocation + subDirectories + fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
