package com.gcu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.business.OrdersBusinessServiceInterface;
import com.gcu.business.UserServiceInterface;
import com.gcu.model.LoginModel;
import com.gcu.model.OrderModel;
import com.gcu.model.UserModel;

@Controller
@RequestMapping("/register")
public class RegisterController
{
	// Class member variable
	// It's an instance variable
	@Autowired
	private UserServiceInterface service;
	
	
	@GetMapping("/")
	public String display(Model model)
	{
		// Display Login Form View
		model.addAttribute("title", "Registration Form");
		model.addAttribute("userModel", new UserModel());
		return "register";
	}
	
	@PostMapping("/doRegister")
	public String doLogin(@Valid UserModel userModel, BindingResult bindingResult, Model model)
	{
		// Check for validation errors
		if(bindingResult.hasErrors())
		{
			model.addAttribute("title", "Login Form");
			return "register"; // Keep user at login page
		}
		
		//System.out.println(String.format("Form with Username of %s and Password of %s", loginModel.getUsername(), loginModel.getPassword()) );
		
		// Get some Orders from the business class
		List<UserModel> orders = service.findAll();
		service.create(userModel);
		
		model.addAttribute("title", "Order Form");
		model.addAttribute("orders", orders);
		
		// Navigate back to the Login view
		return "viewUsers";
	}

}
