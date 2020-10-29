package com.naru.katalk.controller;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;

import static com.naru.katalk.domain.GitHubUrl.CODE;

@RequiredArgsConstructor
@RequestMapping("/users/oauth")
@RestController
public class OAuthController {

    public static final String CLIENT_ID = "8699bba6b253d0b20b3f";

    @GetMapping("/code")
    public ResponseEntity<Object> getCode() {
        HttpHeaders headers = new HttpHeaders();
        URI uri = UriComponentsBuilder.fromUriString(CODE)
                .queryParam("client_id", CLIENT_ID)
                .queryParam("scope", "user")
                .build()
                .toUri();

        headers.setLocation(uri);

        return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
    }

    /*
     GitHub OAuth 설정화면에서 바로 /main 으로 가버리면 뒤에 쿼리로 code=~~~ 가 남는다
     뒤에 나오는 코드를 숨기기 위해 /users/oauth 로 콜백 URL을 보낸 뒤 다시 여기서 /main 으로 리다이렉트 시킨다
     */
    @GetMapping("")
    public ResponseEntity<Object> oauth(@RequestParam String code, HttpServletResponse response)
            throws IOException {
        response.sendRedirect("/docs");
        return new ResponseEntity<>("OAuth Success", HttpStatus.FOUND);
    }
}
