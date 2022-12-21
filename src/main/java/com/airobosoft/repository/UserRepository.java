package com.airobosoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airobosoft.model.Details;

@Repository
public interface UserRepository extends JpaRepository<Details, Integer>{
    
	  public boolean existsByEmail(String email);
	  
	  public Details findByEmail(String email);
}
