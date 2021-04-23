package com.fatec.mom.domain.file.filemanagement;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Component
public class DirectoryManager {

    public void createDirectory(final String path, String dir){

        File directory = new File(path+dir);
        directory.mkdir();
    }
    public void moveFile(final String path, final String destiny){
        File source = new File(path);
        File destination = new File(destiny);
        try{
            Files.copy(source.toPath(),destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
