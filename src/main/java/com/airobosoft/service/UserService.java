package com.airobosoft.service;

import java.util.List;

import com.airobosoft.model.Details;

public interface UserService {
  
	public Details createUser(Details user);

	public boolean checkEmail(String email);

	public boolean checKpassword(String emaill,String password);

	public List<Details> displayall();
	
}
