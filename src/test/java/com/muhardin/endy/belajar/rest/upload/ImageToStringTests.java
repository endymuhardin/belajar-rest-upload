package com.muhardin.endy.belajar.rest.upload;

import java.io.IOException;
import java.util.Base64;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ImageToStringTests {
    @Test
    public void testConvertImageToString() throws Exception{
        String imageString = Base64.getEncoder().encodeToString(
            this.getClass().getResourceAsStream("/gps-1.png")
            .readAllBytes());
        Assertions.assertNotNull(imageString);
        System.out.println("Image Text : " + imageString);
    }    
}