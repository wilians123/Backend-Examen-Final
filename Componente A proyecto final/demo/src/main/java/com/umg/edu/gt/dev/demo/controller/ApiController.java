package com.umg.edu.gt.dev.demo.controller;

import com.umg.edu.gt.dev.demo.service.ApiService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/example")
    public String getExample() {
        return apiService.getExample();
    }

    @PostMapping("/example")
    public String postExample() {
        return apiService.postExample();
    }

    @PutMapping("/example")
    public String putExample() {
        return apiService.putExample();
    }

    @DeleteMapping("/example")
    public String deleteExample() {
        return apiService.deleteExample();
    }
}
