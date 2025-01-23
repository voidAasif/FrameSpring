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

import com.example.j006_fileManipulation.service.FileService;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    public FileService fileService;

    @GetMapping("/upload")
    public String getData(){
        return "working";
    }
    
    @PostMapping("/upload")
    public ResponseEntity<String> postData(@RequestParam("myFileKey") MultipartFile file){
        if (!fileService.uploadData(file)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occur while uploading file");
        }

        return ResponseEntity.ok("file upload successfully");
    }

    @DeleteMapping("/upload")
    public String deleteData(){
        if (fileService.deleteAll()) {   
            return "All file deleted";
        }
        return "File not delete";
    }

}
