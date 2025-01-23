package com.example.j006_fileManipulation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.j006_fileManipulation.service.FileService;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    public FileService fileService;

    @GetMapping("/upload") 
    public String getData(){ //for server testing;
        return "working";
    }
    
    @PostMapping("/upload") //upload file on server;
    public ResponseEntity<String> postData(@RequestParam("myFileKey") MultipartFile file){ //myFileKey is form key;
        if (!fileService.uploadData(file)) { //send file on service;
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occur while uploading file");
        }

        String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(file.getOriginalFilename()+"").toUriString();
        //ServletUriComponentsBuilder.fromCurrentContextPath -> give current path like "http://localhost:8080" or live path;
        //.path("/image/") -> add "/image/" after localhost;
        //.path(file.getOriginalFilename()) -> it add fileName at the end of url;
        //toUriString() -> it convert UriComponentBuilder into String;
        //final string -> http://localhost:8080/image/xyz.jpg;

        return ResponseEntity.ok("file upload successfully || URL: " + fileUrl);
    }

    @DeleteMapping("/upload")
    public String deleteData(){ //empty directory;
        if (fileService.deleteAll()) {   //call service;
            return "All file deleted";
        }
        return "File not delete";
    }

}
