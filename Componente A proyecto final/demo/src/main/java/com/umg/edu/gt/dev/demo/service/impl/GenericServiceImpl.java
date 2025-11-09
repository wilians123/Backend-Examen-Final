package com.umg.edu.gt.dev.demo.service.impl;

import com.umg.edu.gt.dev.demo.dto.GenericEntityDto;
import com.umg.edu.gt.dev.demo.repository.GenericRepository;
import com.umg.edu.gt.dev.demo.repository.entity.GenericEntity;
import com.umg.edu.gt.dev.demo.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GenericServiceImpl implements IGenericService {

    @Autowired
    private GenericRepository genericRepository;

    @Override
    public List<GenericEntityDto> findAll() {
    	List<GenericEntityDto> datos = new ArrayList<>();
    	GenericEntityDto dato = new GenericEntityDto();
        List<GenericEntity> entities = genericRepository.findAll();
        for(GenericEntity entity: entities) {
        	dato.setId(entity.getId());
        	dato.setName(entity.getName());
        	datos.add(dato);
        }        
        return datos;
    }

    @Override
    public GenericEntityDto findById(Long id) {
        Optional<GenericEntity> entity = genericRepository.findById(id);
        GenericEntityDto datos = null;
        //datos.setId(entity.get);
        
        return datos;
    }

    @Override
    public GenericEntityDto save(GenericEntityDto entityArgs) {
    	GenericEntity entity = new GenericEntity();
    	entity.setId(entityArgs.getId());
    	entity.setName(entityArgs.getName());
    	
    	if(entityArgs.isCount()) {
    		//TODO logica para actualizar tabla maestra
    	}
    	
    	genericRepository.save(entity);
    	entityArgs.setId(entity.getId());
    	
        return entityArgs;
    }

    @Override
    public void deleteById(Long id) {
        genericRepository.deleteById(id);
    }
}
