package com.gcu.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcu.business.UserServiceInterface;
import com.gcu.model.UserModel;


/*
 * Album Controller Class.
 * This class implements all the functionality for the Album Services 
 * All CRUD operations are activated in this class.
 * @Annotations Involved : Controller , Request Mapping, GetMapping, Postmapping
 */



/* @Controller - Extends use-case of @Component 
 * @Request mapping - HTTP request to handlers of MVC/Rest Controllers
 */

@Controller
@RequestMapping("/users") 
public class UserController 
{	
	/*
	 * @Autowire albumService, inject AlbumServiceInterface
	 */
	@Autowired
	public UserServiceInterface userService;	
	
	/* Return the viewUsers page
	 * Get Users from the database
	 * @GetMapping - HTTP get Request (allAlbums)
	 * @Param - Model model
	 * @Return viewUsers with all the albums in the list 
	 */
	@GetMapping("/allUsers")
	public String displayUsers(Model model)
	{			
		try
		{
			List<UserModel> users = userService.findAll();
			model.addAttribute("title", "All User");
			model.addAttribute("users", users);	
		}
		catch (Exception e)
		{
			model.addAttribute("title", "Not found...");
		}
		return "viewUsers";
	}
	
	/* This method simply displays the form to create a new User.
	 * This is inserting some values into our text boxes
	 * Return the userCreateForm view
	 * @GetMapping - HTTP get Request (Form)
	 * @Param Model model
	 * @Return userCreateForm with the userModel 
	 */
	@GetMapping("/form")
	public String displayUserForm(Model model)
	{
		try
		{
			model.addAttribute("title", "Create Album Form");
			model.addAttribute("albumModel", new UserModel());		
		}
		catch (Exception e)
		{
			System.out.println("");
		}
		return "albumCreateForm";
	}
	
	// This method will display an user the user clicks on from the list of users
	@GetMapping("/display")
	public String displayOneUserForm(@RequestParam("id") String id, Model model)
	{
		try
		{
			UserModel foundUser = userService.getUserById(id);
			model.addAttribute("title", "User");	
			model.addAttribute("user", foundUser);		
		}
		catch (Exception e)
		{
			model.addAttribute("title", "Not found...");
		}
		return "userDisplayForm";
	}
	
	// This method will compute the edit display form and find it by the ID
	@GetMapping("/editForm")
	public String displayEditForm(@RequestParam("id") String id, Model model)
	{
		try
		{
			UserModel foundUser = userService.getUserById(id);
			model.addAttribute("title", "Edit User");			
			model.addAttribute("user", foundUser);		
		}
		catch (Exception e)
		{
			model.addAttribute("title", "Not found...");
		}
		
		return "userUpdateForm";
	}
	
	
	// This method will process the edit process of the user
	@PostMapping("/edit")
	public String edit(UserModel userModel, BindingResult bindingResult, Model model)
	{
		
		if(bindingResult.hasErrors()) 
		{
			model.addAttribute("title", "Create User Form");
			return "albumUpdateForm";			
		}
		try
		{
			UserModel updatedAlbum = userService.editUser(userModel);	
			model.addAttribute("title", "Albums4You");
			model.addAttribute("albums", updatedAlbum);
		}
		catch (Exception e)
		{
			model.addAttribute("title", "Updating failed...");
		}
		return "viewUsers";
	}	
	
	
	/* This album returns the displayDeleteForm
	 *  Call the data service delete method
	 */
	@GetMapping("/delete")
	public String displayDeleteForm(@RequestParam("id") String id, Model model) 
	{	
		try
		{
			UserModel foundUser = userService.getUserById(id);
	
			model.addAttribute("title", "Are you sure you want to delete?");
			model.addAttribute("user", foundUser);
		}
		catch (Exception e)
		{
			model.addAttribute("title", "Not found...");
		}
		return "DeleteConfirmation";
	}
	
	
	/* This method deletes an album from the database by calling the DeleteUser method from the data service with the selected User's id  as the parameter
	 * Call the data service delete method 
	 */
	@PostMapping("/processDelete")
	public String deleteUser(UserModel user, Model model)
	{	
		System.out.println("Index: " + user);
	
		userService.Delete(user.getId());			
		System.out.println("Deleted User is " + user);
		
		model.addAttribute("title", "User");
		model.addAttribute("user", user);
	
		return "viewUsers";
	}
	
}
