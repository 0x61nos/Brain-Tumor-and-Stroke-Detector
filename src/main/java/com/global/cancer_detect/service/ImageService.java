package com.global.cancer_detect.service;

import com.global.cancer_detect.Entity.ImageModel;
import com.global.cancer_detect.Entity.User;
import com.global.cancer_detect.repository.ImageRepo;
import com.global.cancer_detect.repository.UserRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.*;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class ImageService {
    @Autowired
    ImageRepo imageRepo;
    @Autowired
    UserRepo userRepo;
    @Value("${BASE_URL}")
    private String bASE_URL;
    public ResponseEntity<Object> StoreImade(MultipartFile image) throws IOException {

        //        String url = "https://jsonplaceholder.typicode.com/posts";
        String url = "http://127.0.0.1:5000/predict";
//---------------------------------------------------------------------------
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

        ByteArrayResource fileAsResource = new ByteArrayResource(image.getBytes()){
            @Override
            public String getFilename(){
                return image.getOriginalFilename();
            }
        };
        body.add("image",fileAsResource);
//------------------------------------------------------------------------------------------------------------------
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, requestHeaders);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.POST,requestEntity,Object.class);
//        System.out.println(response.getBody().toString());

//------------------------------------------------------------------------------------------------------------------
        String[] newFileName=response.getBody().toString().split("=");
        System.out.println(newFileName[1]);
        String[] newFileName2=newFileName[2].split("}");
        String path=bASE_URL+"/upload/"+newFileName2[0];
//-------------------------------------------------------------------------------------------------------------------
        String[] newResult=newFileName[1].split(",");
        System.out.println(newResult[0]);
        float result= Float.parseFloat(newResult[0]);
        System.out.println(result);
//-------------------------------------------------------------------------------------------------------------------
//        String path=bASE_URL+"/upload/"+image.getOriginalFilename();
//----------------------------------------------------------------------------------------------------------------------------
        ImageModel imageModel=new ImageModel();
        imageModel.setPath(path);
        imageModel.setResult(result);
//        User user=new User();
//        user=userRepo.getById(1L);
//        imageModel.setUser(user);
        imageRepo.save(imageModel);
//------------------------------------------------------------------------------------------------------------------
//        return ResponseEntity.ok(imageModel);
        return ResponseEntity.ok(response.getBody());
    }


}
