package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.dto.requests.UploadImageRequest;
import com.ubuntucontinues.ubuntu.dto.responses.UploadImageResponse;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class CloudinaryServiceTest {
    @Autowired
    private CloudinaryService cloudinaryService;

    @Test
    public void testThatUserCanAddAndSaveTheirImage() throws UserExistException, IOException {
        UploadImageRequest uploadImageRequest = new UploadImageRequest();

//        UploadImageResponse uploadImageResponse = cloudinaryService.uploadImage(uploadImageRequest);
//        assertThat(uploadImageResponse.getImageUrl()).isNotNull();
    }

}
