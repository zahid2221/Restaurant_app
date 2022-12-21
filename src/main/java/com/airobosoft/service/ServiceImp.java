package com.airobosoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airobosoft.model.Details;
import com.airobosoft.repository.UserRepository;

@Service
public class ServiceImp implements UserService {
	
	@Autowired
	private UserRepository repository;

	@Override
	public Details createUser(Details user) {
		return repository.save(user);
		
	}

	@Override
	public boolean checkEmail(String email) {
		
		return repository.existsByEmail(email);
	}

	@Override
	public boolean checKpassword(String email, String password) {
		Details det= repository.findByEmail(email);
		if(det.getPassword().equals(password))
		{
		   return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public List<Details> displayall() {
		return repository.findAll();
	}
	
}
