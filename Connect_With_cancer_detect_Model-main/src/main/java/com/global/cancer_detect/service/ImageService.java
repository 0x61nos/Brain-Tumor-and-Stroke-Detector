package com.global.cancer_detect.service;

import com.global.cancer_detect.Entity.ImageModel;
import com.global.cancer_detect.Entity.TokenInfo;
import com.global.cancer_detect.Entity.User;
import com.global.cancer_detect.repository.ImageRepo;
import com.global.cancer_detect.repository.TokenInfoRepo;
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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class ImageService {
    @Autowired
    ImageRepo imageRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    TokenInfoService tokenInfoService;
    @Value("${BASE_URL}")
    private String bASE_URL;

    public static String getBearerTokenHeader() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
    }

    public ResponseEntity<Object> StoreImade(MultipartFile image, Long typeModel, Long id) throws IOException {

        //        String url = "https://jsonplaceholder.typicode.com/posts";
        String url = "http://127.0.0.1:5000/predict";
        String url2 = "http://127.0.0.1:5000/predict2";
//----------------------------------------------------------------------------------------------------------------------
        String jwtToken = getBearerTokenHeader().substring("Bearer ".length());
//        System.out.println(jwtToken);
        Optional<TokenInfo> accessToken = tokenInfoService.findByAccessToken(jwtToken);
//        System.out.println(accessToken.isPresent());
        if (!(accessToken.isPresent())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            if ((accessToken.get().getUser().getId())!=id) {
                return ResponseEntity.badRequest().body("id of User is faild");
//                  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
//----------------------------------------------------------------------------------------------------------------------
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

        ByteArrayResource fileAsResource = new ByteArrayResource(image.getBytes()) {
            @Override
            public String getFilename() {
                return image.getOriginalFilename();
            }
        };
        body.add("image", fileAsResource);
//------------------------------------------------------------------------------------------------------------------
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, requestHeaders);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> response ;
        if(typeModel==1){
             response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Object.class);
        }
        else{
             response = restTemplate.exchange(url2, HttpMethod.POST, requestEntity, Object.class);
        }
//        System.out.println(response.getBody().toString());

//------------------------------------------------------------------------------------------------------------------
        String[] newFileName = response.getBody().toString().split("=");
        System.out.println(newFileName[1]);
        String[] newFileName2 = newFileName[2].split("}");
        String path = bASE_URL + "/upload/" + newFileName2[0];
//-------------------------------------------------------------------------------------------------------------------
        String[] newResult = newFileName[1].split(",");
        System.out.println(newResult[0]);
        float result = Float.parseFloat(newResult[0]);
        System.out.println(result);
//-------------------------------------------------------------------------------------------------------------------
//        String path=bASE_URL+"/upload/"+image.getOriginalFilename();
//----------------------------------------------------------------------------------------------------------------------------
        ImageModel imageModel = new ImageModel();
        imageModel.setPath(path);
        imageModel.setResult(result);
        if(accessToken.isPresent()){
            imageModel.setUser(accessToken.get().getUser());
//        imageModel.setUser(userRepo.getById(id));         //مش صح دي لان لو يوزر مش تمام و استخدم بوست مان و حطلي اي اي دي بتاع يوزر تاني كدا هحفظ الصورة !
            imageRepo.save(imageModel);
        }


//------------------------------------------------------------------------------------------------------------------
//        return ResponseEntity.ok(imageModel);
        return ResponseEntity.ok(response.getBody());
    }
}
