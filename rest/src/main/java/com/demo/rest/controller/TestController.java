package com.demo.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @GetMapping("/leak/test")
    public ResponseEntity test(){
        Map result = new HashMap<>();
        result.put("rtn", 0);
        return ResponseEntity.ok(result);
    }
}
