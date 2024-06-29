package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.dto.requests.UploadImageRequest;
import com.ubuntucontinues.ubuntu.dto.responses.CreatePostResponse;
import com.ubuntucontinues.ubuntu.dto.responses.UploadImageResponse;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class CloudinaryServiceTest {
    @Autowired
    private CloudinaryService cloudinaryService;

    @Test
    public void testThatUserCanAddAndSaveTheirImage() throws UserExistException, IOException {
        UploadImageRequest uploadImageRequest = new UploadImageRequest();
        String location = "C:\\Users\\Dell\\Downloads\\workAsIcon.jpeg";
        File file = new File(location);
        FileInputStream inputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("filename",inputStream);
        UploadImageResponse response = cloudinaryService.uploadImage(multipartFile);

        System.out.println(response.getImageUrl());
        assertThat(response).isNotNull();
    }

}
