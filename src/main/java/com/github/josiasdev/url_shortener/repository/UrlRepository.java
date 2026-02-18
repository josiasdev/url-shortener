package com.github.josiasdev.url_shortener.repository;

import com.github.josiasdev.url_shortener.entities.UrlEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepository extends MongoRepository<UrlEntity, String> {
}
