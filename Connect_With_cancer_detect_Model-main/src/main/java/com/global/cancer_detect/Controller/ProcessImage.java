package com.global.cancer_detect.Controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.global.cancer_detect.service.ImageService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
//اشغلو لما ارن الفرونت
@CrossOrigin()
@AllArgsConstructor
@NoArgsConstructor
public class ProcessImage {

    @Autowired
    ImageService imageService;

    //    @PostMapping("/upload")
//    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile image) throws IOException {
//        RestTemplate restTemplate = new RestTemplate();
//
//        String url = "http://127.0.0.1:5000/predict";  // Update with Python API URL
////        String filename = image.getOriginalFilename();
////        byte[] imageBytes = image.getBytes();
//
////        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
////        parts.add("image", image);
////        -----------------------------------------------------------------------------------------------
//
////        HttpHeaders headers = new HttpHeaders();
////        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
////        MultiValueMap<String, Object> body
////                = new LinkedMultiValueMap<>();
////        body.add("image", image);
////        HttpEntity<MultiValueMap<String, Object>> requestEntity
////                = new HttpEntity<>(body, headers);
//
////--------------------------------------------------------------------------------------------------
//
////        String serverUrl = "http://127.0.0.1:5000/predict";
////        String filename = image.getOriginalFilename();
////        byte[] imageBytes = image.getBytes();
////
////        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
////        parts.add("image", imageBytes);
////
////        ResponseEntity<String> response = restTemplate.postForEntity(url, parts, String.class);
//
////------------------------------------------------------------------------------------------------------------
////        RestTemplate restTemplate = new RestTemplate();
////---------------------------------------------------------------------------------------------------------------
////        // Create a MultipartEntity with the image file
////        HttpEntity<MultipartFile> imageEntity = new HttpEntity<>(image, headers);
////
////        // Use restTemplate.postForEntity with a single HttpEntity for the image
////        ResponseEntity<String> response = restTemplate.postForEntity(url, imageEntity, String.class);
////----------------------------------------------------------------------------------------------------------
////        ResponseEntity<String> response = restTemplate
////                .postForEntity(serverUrl, requestEntity, String.class);
//
////        ------------------------------------------------------------------------------------------------
//
////        ResponseEntity<String> response = restTemplate.postForEntity(url, parts, String.class);
////--------------------------------------------------------------------------------------------------------
//
//
//        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
//        parts.add("Content-Type", "image/jpeg");
//        parts.add("image", image);
//
//        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST,
//                new HttpEntity<MultiValueMap<String, Object>>(parts),
//                String.class); // make sure to use the generic type argument
//        System.out.println(response.getStatusCode());
//        return ResponseEntity.ok("Okkkkkk");
////----------------------------------------------------------------------------------------------------------
////        if (response.getStatusCode() == HttpStatus.OK) {
////            return ResponseEntity.ok(response.getBody().toString());  // Return ML prediction
////        } else {
////            return ResponseEntity.status(response.getStatusCode()).body("Error uploading image");
////        }
//    }
//
//
//    @GetMapping("/up")
//    public ResponseEntity<Object> up() throws IOException {
//
////---------------------------------------------------------------------------------------------------------
//
////        String url = "https://jsonplaceholder.typicode.com/posts";
//////        String url = "http://127.0.0.1:5000/shrook";
////        HttpHeaders headers = new HttpHeaders();
////        headers.setContentType(MediaType.APPLICATION_JSON);
////        Map<String, String> requestBody = new HashMap<>();
////        requestBody.put("userId", "540");
////        requestBody.put("title", "Hello World");
////        requestBody.put("body", "Lorem ipsum dolor sit amet");
////
////        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);
////
////        RestTemplate restTemplate = new RestTemplate();
////        ResponseEntity<Object> response = restTemplate.exchange(
////                url,
////                HttpMethod.POST,
////                requestEntity,
////                Object.class);
//////        List<Object> objects = response.getBody();
////
////        return ResponseEntity.ok(response.getBody());
//
////---------------------------------------------------------------------------------------------------
//
//        String url = "https://jsonplaceholder.typicode.com/posts";
////        String url = "http://127.0.0.1:5000/shrook";
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        Map<String, String> requestBody = new HashMap<>();
//        requestBody.put("userId", "540");
//        requestBody.put("title", "Hello World");
//        requestBody.put("body", "Lorem ipsum dolor sit amet");
//
//        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);
//
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<Object> response = restTemplate.exchange(
//                url,
//                HttpMethod.POST,
//                requestEntity,
//                Object.class);
////        List<Object> objects = response.getBody();
//
//        return ResponseEntity.ok(response.getBody());
//    }

//--------------------------------------------------------------------------------------------------------

    @PostMapping("/upload/{id}")//id of user
    public ResponseEntity<Object> uploadImade(@RequestParam("image") MultipartFile image,@RequestParam("typeModel") Long typeModel,@PathVariable() Long id ) throws IOException {
        return imageService.StoreImade(image,typeModel,id);
    }

}