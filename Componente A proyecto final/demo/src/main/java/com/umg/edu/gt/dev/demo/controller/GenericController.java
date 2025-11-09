package com.umg.edu.gt.dev.demo.controller;

import com.umg.edu.gt.dev.demo.dto.GenericEntityDto;
import com.umg.edu.gt.dev.demo.service.IGenericService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/generic")
public class GenericController {

    @Autowired
    private IGenericService genericService;
    
    @GetMapping
    public List<GenericEntityDto> getAll() {
        return genericService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericEntityDto> getById(@PathVariable Long id) {
    	GenericEntityDto entity = genericService.findById(id);
        return entity != null ? new ResponseEntity<>(entity, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public GenericEntityDto create(@RequestBody GenericEntityDto entity) {
        return genericService.save(entity);
    }

    @PutMapping
    public GenericEntityDto update(@RequestBody GenericEntityDto entity) {
        return genericService.save(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        genericService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
