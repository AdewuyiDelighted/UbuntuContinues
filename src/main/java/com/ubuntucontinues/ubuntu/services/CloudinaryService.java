package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.dto.requests.UploadImageRequest;
import com.ubuntucontinues.ubuntu.dto.responses.UploadImageResponse;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryService {

    UploadImageResponse uploadImage(MultipartFile multipartFile) throws IOException;
}
