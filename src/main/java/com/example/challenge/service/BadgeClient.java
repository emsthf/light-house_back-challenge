package com.example.challenge.service;

import com.example.challenge.controller.Badge;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Badge", url = "http://localhost:8080")
public interface BadgeClient {

    @GetMapping("/api/badge/{id}")
    public Badge getBadgeById(@PathVariable("id") Long id);
}
