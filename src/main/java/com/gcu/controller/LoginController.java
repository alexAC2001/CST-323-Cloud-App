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

import com.gcu.business.OrdersBusinessServiceInterface;
import com.gcu.model.LoginModel;
import com.gcu.model.OrderModel;

@Controller
@RequestMapping("/login")
public class LoginController
{
	// Class member variable
	// It's an instance variable
	@Autowired
	private OrdersBusinessServiceInterface service;
	
	@GetMapping("/")
	public String display(Model model)
	{
		// Display Login Form View
		model.addAttribute("title", "Login Form");
		model.addAttribute("loginModel", new LoginModel());
		return "login";
	}
	
	@PostMapping("/doLogin")
	public String doLogin(@Valid LoginModel loginModel, BindingResult bindingResult, Model model)
	{
		// Check for validation errors
		if(bindingResult.hasErrors())
		{
			model.addAttribute("title", "Login Form");
			return "login"; // Keep user at login page
		}
		
		System.out.println(String.format("Form with Username of %s and Password of %s", loginModel.getUsername(), loginModel.getPassword()) );
		
		// Get some Orders from the business class
		List<OrderModel> orders = service.findAll();
		
		model.addAttribute("title", "Order Form");
		model.addAttribute("orders", orders);
		
		// Navigate back to the Login view
		return "orders";
	}

}
