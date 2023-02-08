package com.muhardin.endy.belajar.rest.upload.dto;

import lombok.Data;

@Data
public class ImageUploadResponseDto {
    private String status;
    private String filename;
    private Long size;
    private String type;
}
