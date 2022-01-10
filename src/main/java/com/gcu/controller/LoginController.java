package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.model.LoginModel;

@Controller
@RequestMapping("/login")
public class LoginController
{
	@GetMapping("/")
	public String display(Model model)
	{
		// Display Login Form View
		model.addAttribute("title", "Login Form");
		model.addAttribute("loginModel", new LoginModel());
		return "login";
	}
	
	@PostMapping("/doLogin")
	public String doLogin(LoginModel loginModel, BindingResult bindingResult, Model model)
	{
		System.out.println(String.format("Form with Username of % and a Password of %", loginModel.getUsername(), loginModel.getPassword()));
		
		// Navigate back to the Login view
		return "login";
	}

}
