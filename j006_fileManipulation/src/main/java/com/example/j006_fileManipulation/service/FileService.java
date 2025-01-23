package com.example.j006_fileManipulation.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FileService {

    public String uploadPath = "src/main/resources/static/image/"; //path where images are stored;

    public boolean uploadData(MultipartFile multiPartFile){ //accept file;
        if (!multiPartFile.isEmpty()) {
            System.out.println();
            System.out.println();
            System.out.println( "File Data:  \n\n"+           //print file data;
                            multiPartFile.getClass()+"\n"+
                            multiPartFile.getContentType()+"\n"+
                            multiPartFile.getName()+"\n"+
                            multiPartFile.getOriginalFilename()+"\n"+
                            multiPartFile.getResource()+"\n"+
                            multiPartFile.getSize()
                        );
        
        try {

        // traditional method to read and write file; use "io" class;

            // //store image data into byte array;
            // InputStream inputStream = multiPartFile.getInputStream(); //input stream convert input MultiPartFile into byte array;
            // byte[] fileByte = new byte[inputStream.available()]; //init image size array;
            // inputStream.read(fileByte); //write image data into same size array;

            // //write byte array into upload directory;
            // FileOutputStream fos = new FileOutputStream(uploadPath+multiPartFile.getOriginalFilename()); //select path where image will write;
            // fos.write(fileByte); //write byte array into image data;

            // inputStream.close(); //close resources;
            // fos.close();
        
         

        //use "nio" class;
            //work same as above io method;

            //copy method take three values: (InputStream, Target Path, CopyOption)
            Files.copy(multiPartFile.getInputStream(), //Input Stream to store on given target path;
            Path.of( uploadPath+multiPartFile.getOriginalFilename() ), //path with file name, not accept direct string, so we use path class;
            StandardCopyOption.REPLACE_EXISTING); //CopyOption 


        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
            return true;
        }

        return false;
    }

    public boolean deleteAll(){ //empty images directory;
        try {
            File fileDir = new File(uploadPath); //get image path;
            if (fileDir.isDirectory()) { //check directory is empty or not;
                File[] fileList = fileDir.listFiles(); //store list of file in directory into file array;
                for(File ele: fileList){ //traverse files array and put file into ele oneByOne;
                    ele.delete(); //delete file stored in ele;
                    System.out.println("deleting: " + ele); //print log;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
