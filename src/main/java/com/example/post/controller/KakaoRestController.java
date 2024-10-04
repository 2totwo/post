package com.example.post.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class KakaoRestController {

    private final String apiKey="764bdfe574bc374c0644a63fbc649a0f";
    @GetMapping("/books")
    public ResponseEntity<?> books(String title){ // return type 모르면 그냥 <?>
        // System.out.println(title);
        final String url="https://dapi.kakao.com/v3/search/book?query="+title;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK "+apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response= restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        // url, 전송방식, header, 결과를 String으로 담기
        return ResponseEntity
                .ok() // 200
                .contentType(MediaType.APPLICATION_JSON_UTF8) // JSON
                .body(response.getBody()); // 실제 받은 데이터
    }
}

