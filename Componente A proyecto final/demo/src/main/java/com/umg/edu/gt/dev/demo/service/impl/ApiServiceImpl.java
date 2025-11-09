package com.umg.edu.gt.dev.demo.service.impl;

import com.umg.edu.gt.dev.demo.service.ApiService;
import org.springframework.stereotype.Service;

@Service
public class ApiServiceImpl implements ApiService {

    @Override
    public String getExample() {
        return "GET Response from Service";
    }

    @Override
    public String postExample() {
        return "POST Response from Service";
    }

    @Override
    public String putExample() {
        return "PUT Response from Service";
    }

    @Override
    public String deleteExample() {
        return "DELETE Response from Service";
    }
}
