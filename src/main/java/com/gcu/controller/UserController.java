package com.gcu.controller;

import java.util.List;

import javax.validation.Valid;

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
	
	/*
	 * @Autowire albumDataService, inject AlbumDataAccessInterface
	 */
	//@Autowired 
	//public AlbumDataAccessInterface<?> albumDataService;
	
	
	/* Set the title attribute (from common.html) to Albums4You
	 * Return the viewAlbums page
	 * Get Albums from the database
	 * @GetMapping - HTTP get Request (allAlbums)
	 * @Param - Model model
	 * @Return viewAlbums with all the albums in the list 
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
	
	/* This method simply displays the form to create a new Album.
	 * This is inserting some values into our text boxes
	 * Return the albumCreateForm view
	 * @GetMapping - HTTP get Request (Form)
	 * @Param Model model
	 * @Return albumCreateForm with the albummodel 
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
	
	/* This method will display an album the user clicks on from the list of albums
	 * Make a list that will contain the Tracks of the desired album
	 * Set the title attribute (from common.html) to Albums4You
	 *  Set the attribute to tracks, which is the list of tracks from the album
	 *  Return the albumUpdateAlbum page with the Album's data
	 *  @GetMapping - HTTP get Request display
	 *  @RequestParam - Request the ID 
	 *  @Param String Id of album, and model
	 *  @Return albumDisplayForm witha list 
	 */

	@GetMapping("/display")
	public String displayOneUserForm(@RequestParam("id") String id, Model model)
	{
		try
		{
			UserModel foundUser = userService.getUserById(id);
			//System.out.println("Album info: " + foundAlbum.getTitle());
			//System.out.println("Album info: " + foundAlbum.getArtist());
 
			model.addAttribute("title", "User");	
			model.addAttribute("user", foundUser);		
		}
		catch (Exception e)
		{
			model.addAttribute("title", "Not found...");
		}
		return "userDisplayForm";
	}
	
	/* This method will compute the edit display form and find it by the ID
	 * Set the title attribute (from common.html) to Albums4You
	 * Add an albums attribute, which is the found album
	 * Return the albumUpdateAlbum page with the Album's data
	 * @GetMapping HTTP request Edit Form
	 * @RequestParam - Grab the ID of the album
	 * @Param - String id and model of the album 
	 * @Return albumUpdateForm with a found album
	 * 
	 */
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
	
	
	/* This method will process the edit process of the album.
	 * If there were errors in the fields, keep the user at the albumCreateForm
	 * Create a AlbumModel object and set it equal the albumservice.edit album and edit the albu model passed.
	 * pass in the model attribute of the updated album.
	 * @Postmapping - maps HTTP POST requests onto specific handler methods. (/edit)
	 * @Param - Albummodel, Binding result, and Model
	 * @Return an updated album, and the view albums page 
	 * 
	 */
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
	 * Add title attribute to the returned index page
	 * @GetMapping HTTP request (/delete) 
	 * @RequestParam request the ID of the ID
	 * @Param - String ID and pass the Model for the album
	 * @Return the desired album and the delete confirmation page 
	 * 
	 * 
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
	
	
	/* This method deletes an album from the database by calling the DeleteAlbum method from the data service with the selected Album's id  as the parameter
	 * Call the data service delete method
	 * Add title attribute to the returned index page
	 * Pass the album model through the model attribute page
	 * @Exceptions- UpdatedFailException , Null Pointer Exception
	 * @PostMapping - maps HTTP POST requests onto specific handlers (processdelete)
	 * @Param AlbumModel, Model and Model
	 * @Return the index of the web page 
	 * 
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
