package com.example.demo.controller;

import com.example.demo.dto.ResponseDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("testGetMapping")
    public String testContoller() {
        return "Hello World!";
    }

    @GetMapping("/{id}")
    public String testControllerWithPathVaiables(@PathVariable(required = false) int id) {
        return "Hello World! ID " + id;
    }


    @GetMapping("/testResponseBody")
    public ResponseDTO<String> testContollerResonseBody() {
        List<String> list = new ArrayList<>();
        list.add("Hello World! I'm ResonseDTO");
        ResponseDTO<String> response = ResponseDTO.<String>builder()
            .data(list)
            .build();

        return response;
    }


    @GetMapping("/testResponseEntity")
    public ResponseEntity<?> testControllerResponseEntity() {
        List<String> list = new ArrayList<>();
        list.add("Hello World! I am ResponseEntity. ANd you got 400!");
        ResponseDTO<String> response = ResponseDTO.<String>builder()
            .data(list)
            .build();
        return ResponseEntity.badRequest().body(response);
    }

}
