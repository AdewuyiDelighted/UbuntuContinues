package com.ubuntucontinues.ubuntu.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ubuntucontinues.ubuntu.data.models.Image;
import com.ubuntucontinues.ubuntu.data.repositories.ImageRepository;
import com.ubuntucontinues.ubuntu.dto.responses.UploadImageResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@AllArgsConstructor
public class UbuntuCloudinaryService implements CloudinaryService {
    private final Cloudinary cloudinary;
    private final UserService userService;
    private final ImageRepository imageRepository;

//    @Override
//    public UploadImageResponse uploadImage(UploadImageRequest uploadImageRequest) throws IOException {
//        System.out.println(uploadImageRequest.getUserId());
//        System.out.println(uploadImageRequest.getMultipartFile());
//        Map uploadedFile = cloudinary.uploader()
//                .upload(uploadImageRequest.getMultipartFile().getBytes(), ObjectUtils.emptyMap());
//        saveFile(uploadImageRequest, uploadedFile);
//
//        UploadImageResponse uploadImageResponse = new UploadImageResponse();
//        uploadImageResponse.setImageUrl(uploadedFile.get("url").toString());
//
//        return uploadImageResponse;
//    }
    @Override
    public UploadImageResponse uploadImage(MultipartFile multipartFile) throws IOException {

        Map uploadedFile = cloudinary.uploader()
                .upload(multipartFile.getBytes(), ObjectUtils.emptyMap());
        saveFile(uploadedFile);

        UploadImageResponse uploadImageResponse = new UploadImageResponse();
        uploadImageResponse.setImageUrl(uploadedFile.get("url").toString());

        return uploadImageResponse;
    }

    private void saveFile(Map uploadedFile) {
        Image image = Image.builder()
//                .user(userService.findBY(uploadImageRequest.getUserId()))
                .url(uploadedFile.get("url").toString())
                .build();
        imageRepository.save(image);
    }
}
