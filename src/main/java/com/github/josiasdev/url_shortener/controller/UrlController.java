package com.github.josiasdev.url_shortener.controller;

import com.github.josiasdev.url_shortener.dto.ShortenUrlRequest;
import com.github.josiasdev.url_shortener.dto.ShortenUrlResponse;
import com.github.josiasdev.url_shortener.entities.UrlEntity;
import com.github.josiasdev.url_shortener.repository.UrlRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class UrlController {
    private final UrlRepository urlRepository;

    public UrlController(UrlRepository urlRepository){
        this.urlRepository = urlRepository;
    }
    @PostMapping(value = "/shorten-url")
    public ResponseEntity<ShortenUrlResponse> shortenUrl(@RequestBody ShortenUrlRequest request, HttpServletRequest servletRequest){
        String id;
        do {
             id = RandomStringUtils.randomAlphanumeric(5, 10);
        } while(urlRepository.existsById(id));

        urlRepository.save(new UrlEntity(id, request.url(), LocalDateTime.now().plusMinutes(1)));

        var redirectUrl = servletRequest.getRequestURL().toString().replace("shorten-url", id);




        return ResponseEntity.ok(new ShortenUrlResponse(redirectUrl));
    }
}
