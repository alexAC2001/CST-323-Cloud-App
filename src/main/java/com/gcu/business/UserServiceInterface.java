package com.gcu.business;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.gcu.entity.UserEntity;
import com.gcu.model.UserModel;


/*
 * 
 * Interface for the UserService class. 
 * @Param, UserModel, Binding result, Model
 */
public interface UserServiceInterface 
{
	public void init();
	public void destroy();
	public String doRegister(UserModel userModel, BindingResult bindingResult, Model model);

	UserEntity create(UserModel user);

	List<UserModel> findAll();
	
	public boolean login(UserModel user);

	public UserModel getUserById(String id) ;
	
	public void Delete(Long i);
	
	public UserModel editUser(UserModel t);
	
	
}
