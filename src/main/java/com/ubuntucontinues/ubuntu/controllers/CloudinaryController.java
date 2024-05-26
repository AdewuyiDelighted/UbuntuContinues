package com.ubuntucontinues.ubuntu.controllers;

import com.ubuntucontinues.ubuntu.dto.requests.UploadImageRequest;
import com.ubuntucontinues.ubuntu.dto.responses.UploadImageResponse;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;
import com.ubuntucontinues.ubuntu.services.ChatMessageService;
import com.ubuntucontinues.ubuntu.services.ChatRoomService;
import com.ubuntucontinues.ubuntu.services.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class CloudinaryController {

    @Autowired
    private CloudinaryService cloudinaryService;


    @GetMapping("/uploadImage")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
//        System.out.println(uploadImageRequest.getUserId());
//        System.out.println(uploadImageRequest.getMultipartFile());
        System.out.println("I got here");
        return ResponseEntity.ok(cloudinaryService.uploadImage(file));
    }

}
