package com.oose.lamport.controllers.helloworld;

import com.oose.lamport.controllers.helloworld.dto.HelloWorldResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/helloworld")
public class HelloWorldController {
  @GetMapping("")
  public ResponseEntity<HelloWorldResponseDto> get () {
    return new ResponseEntity<>(new HelloWorldResponseDto("Hello world!"), HttpStatus.OK);
  }
}
