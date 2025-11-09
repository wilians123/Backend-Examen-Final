package com.umg.edu.gt.dev.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.umg.edu.gt.dev.demo.repository.entity.GenericEntity;

@Repository
@EnableJpaRepositories
public interface GenericRepository extends JpaRepository<GenericEntity, Long> {
	
}
