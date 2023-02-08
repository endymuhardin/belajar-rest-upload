package com.muhardin.endy.belajar.rest.upload.dto;

import lombok.Data;

@Data
public class ImageUploadDto {
    private String name;
    private String type;
    private byte[] content;
}
