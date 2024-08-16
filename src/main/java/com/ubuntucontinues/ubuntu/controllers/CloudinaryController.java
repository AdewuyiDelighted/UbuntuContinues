package com.ubuntucontinues.ubuntu.controllers;

import com.ubuntucontinues.ubuntu.dto.requests.UploadImageRequest;
import com.ubuntucontinues.ubuntu.dto.responses.ApiResponse;
import com.ubuntucontinues.ubuntu.dto.responses.UploadImageResponse;
import com.ubuntucontinues.ubuntu.exceptions.EventAlreadyExistException;
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
@RequestMapping("/ubuntu/cloudinary")
public class CloudinaryController {

    @Autowired
    private CloudinaryService cloudinaryService;


    @GetMapping("/uploadImage")
    public ResponseEntity<ApiResponse<?>> uploadImage(@RequestParam("file") MultipartFile file)  {
        try {
            return ResponseEntity.ok(new ApiResponse<>(true,cloudinaryService.uploadImage(file)));
        }catch (IOException exception) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage()));
        }
    }


}
