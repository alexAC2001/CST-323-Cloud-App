package com.gcu.data;

import java.util.List;

import com.gcu.entity.UserEntity;
import com.gcu.model.UserModel;

// This interface uses Generics so the UserEntity can be injected
public interface UserDataAccessInterface <T>
{
	// Methods that will be implemented
	public List<T> findAll();
	public UserEntity create(UserModel user);
	public boolean login(UserModel user);
	public T findByUsername(String username);
	public UserEntity update(UserModel user);	
	
}
