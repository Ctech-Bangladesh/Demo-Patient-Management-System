package com.demo.oauth2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/api/access")
public class AccessURL {

  @GetMapping
  public Mono<String> home() {
    return Mono.just("Welcome");
  }
}
