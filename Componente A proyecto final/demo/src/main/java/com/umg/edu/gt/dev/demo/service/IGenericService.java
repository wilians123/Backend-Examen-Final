package com.umg.edu.gt.dev.demo.service;

import java.util.List;

import com.umg.edu.gt.dev.demo.dto.GenericEntityDto;

public interface IGenericService {
    List<GenericEntityDto> findAll();
    GenericEntityDto findById(Long id);
    GenericEntityDto save(GenericEntityDto entity);
    void deleteById(Long id);
}
