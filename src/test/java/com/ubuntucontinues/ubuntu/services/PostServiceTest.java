package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.dto.requests.CreatePostRequest;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest
class PostServiceTest {
    @Autowired
    private PostService postService;

    @Test
    public void testThatUserCanNotPostIfTheUserIsNotActivated(){
        CreatePostRequest request = new CreatePostRequest();
        request.setUserId("200");
        assertThrows(UserExistException.class, ()-> postService.post(request, null));
    }

//    @Test
//    public void testThatUserCanCreateAPost() throws IOException {
//        CreatePostRequest request = new CreatePostRequest();
//        request.setTitle("Today Gist and Blog");
//        request.setUserId("12");
//        MultipartFile multipartFile = new MockMultipartFile("file", new FileInputStream(new File("")));
//        request.setMultipartFile();
//        request.setBody("This is my day of greater things");
//    }


}