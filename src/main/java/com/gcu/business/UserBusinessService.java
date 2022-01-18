package com.gcu.business;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.gcu.data.UserDataService;
import com.gcu.model.UserModel;
import com.gcu.entity.UserEntity;
import com.gcu.repository.RegisterRepository;
 
/* This class implements from the UserServiceInterface to support the business service methods
 * The logic for the CRUD operations are performed here
 * 
 */
public class UserBusinessService implements UserServiceInterface
{
	/*
	 * @Autowire UserDataService
	 */
	@Autowired
	UserDataService service;

	
	/*
	 * @Autowire RegisterRepository
	 */
	@Autowired 
	RegisterRepository regservice;
	
	
	/*
	 * @Return Create Bean Service
	 * Print Statement checking the method being called on.
	 */
	@Override
	public void init() 
	{
		System.out.println("CredentialService.init()");
	}
	
	/*
	 * @Return Delete Bean Service
	 * Print Statement checking the method being called on.
	 */
	
	@Override
	public void destroy() 
	{
		System.out.println("CredentialsService.destroy()");
	}

	public UserModel ConvertEntity(UserEntity model) 
	{	
		return new UserModel(model.getId(), model.getFirstName(), model.getLastName(), model.getUsername(), model.getPassword());
	}
	
	/* This class registers a new user 
	 * If Data validation fails, keep the user at the register view
	 * This adds the attribute, loginModel (read in login.html), with a new CredentialsModel object
	 * Set the title (from common.html) to a new string, Login Form
	 * @Param UserModel registerModel
	 * @Param BindingResult bindingResult
	 * @Param Model model
	 * @Return a model attribute UserModel and set the title to login form.
	 */
	@Override
	public String doRegister(@Valid UserModel registerModel, BindingResult bindingResult, Model model) 
	{
	
		if(bindingResult.hasErrors()) 
		{
			model.addAttribute("title", "Registration Form");
			return "register";
		}
		model.addAttribute("credentialsModel", new UserModel());
		model.addAttribute("title", new String("Login Form"));

		return "login";			
	}	
	
	/* Create a Register Profile
	 * @Param UserModel user. 
	 * @Return a user object.(Its passed as a entity to data base, Model to the front end) 
	 */
	@Override
	public UserEntity create(UserModel user)
	{
		UserEntity newUser = service.create(user);		

		return new UserEntity(newUser.getId(), user.getFirstName(), newUser.getLastName(),newUser.getUsername(), newUser.getPassword());
	}

	/*
	 * Login Method
	 * Referencing the service from the DataService class.
	 * @Param UserModel (user) 
	 * @Return call on the login method from the DataService class and return it.
	 */
	@Override
	public boolean login(UserModel user) 
	{
		return service.login(user);		
	}
	
	
	/* 
	 * Find all the Users in the application
	 * Get all the Entity Albums using the findAll() method from the AlbumDataService class.
	 * Iterate over the Entity Albums and create a list of Domain Albums
	 * @Return UserDomain ( which is the list of the UserModels) 
	 */
	@Override
	public List<UserModel> findAll() 
	{
		List<UserEntity> userEntity = service.findAll();
		List<UserModel> userdomain = new ArrayList<UserModel>();
		for(UserEntity entity : userEntity)
		{
			userdomain.add(new UserModel(entity.getId(), entity.getFirstName(),entity.getLastName(),entity.getUsername(),entity.getPassword()));
		}
		return userdomain;	
	}
	
	@Override
	public UserModel getUserById(String id) 
	{
		UserEntity userEntity = service.findById(id);
		return ConvertEntity(userEntity);
	}
	
	@Override
	public UserModel editUser(UserModel user) 
	{
		// Call the service update method
		UserEntity userEntity = service.update(user);
		
		// Return the Model
		return ConvertEntity(userEntity);
	}
		
	/* Delete a User
	 * Call on the DeleteUser Method from the DataService to delete the properties.
	 * @Param. String ID
	 * @Return A deleted user
	 */
	@Override
	public void Delete(Long id) 
	{
		System.out.println("In the business service");
		service.DeleteUser(id);
	}

}
