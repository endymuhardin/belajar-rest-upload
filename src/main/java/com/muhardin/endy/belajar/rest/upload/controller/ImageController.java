package com.muhardin.endy.belajar.rest.upload.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.muhardin.endy.belajar.rest.upload.dto.ImageUploadDto;
import com.muhardin.endy.belajar.rest.upload.dto.ImageUploadResponseDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ImageController {
    private final Path fileStorageLocation;
    
    public ImageController (@Value("${upload.dir}") String uploadDir){
        this.fileStorageLocation = Paths.get(uploadDir)
        .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    @PutMapping("/upload")
    public ImageUploadResponseDto uploadImage(@RequestBody ImageUploadDto img){
        ImageUploadResponseDto responseDto = new ImageUploadResponseDto();
        try {
            String filename = UUID.randomUUID().toString()+ img.getName().substring(img.getName().lastIndexOf("."));
            Path targetLocation = fileStorageLocation.resolve(filename);
            Files.write(targetLocation, img.getContent());
            
            responseDto.setFilename(filename);
            responseDto.setSize(Files.size(targetLocation));
            responseDto.setType(img.getType());
            responseDto.setStatus("success");
            return responseDto;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            responseDto.setStatus(e.getMessage());
            return responseDto;
        }
        
    }
}
