package com.example.j006_fileManipulation.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.SimpleFileVisitor;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FileService {

    public String uploadPath = "src/main/resources/static/image/";

    public boolean uploadData(MultipartFile file){
        if (!file.isEmpty()) {
            System.out.println();
        System.out.println();
        System.out.println( "File Data:  \n\n"+
                            file.getClass()+"\n"+
                            file.getContentType()+"\n"+
                            file.getName()+"\n"+
                            file.getOriginalFilename()+"\n"+
                            file.getResource()+"\n"+
                            file.getSize()
                        );
        
        try {
            //store image data into byte array;
            InputStream inputStream = file.getInputStream();
            byte[] fileByte = new byte[inputStream.available()];
            inputStream.read(fileByte);

            //write byte array into upload directory;
            FileOutputStream fos = new FileOutputStream(uploadPath+file.getOriginalFilename());
            fos.write(fileByte);

            inputStream.close();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
            return true;
        }

        return false;
    }

    public boolean deleteAll(){
        try {
            File fileDir = new File(uploadPath);
            if (fileDir.isDirectory()) {
                File[] fileList = fileDir.listFiles();
                for(File ele: fileList){
                    fileDir.delete();
                    System.out.println("deleting: " + ele);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
