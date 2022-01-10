package com.gcu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.model.LoginModel;
import com.gcu.model.OrderModel;

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
		System.out.println(String.format("Form with Username of %s and Password of %s", loginModel.getUsername(), loginModel.getPassword()) );
		
		List<OrderModel> orders = new ArrayList<OrderModel>();
		orders.add(new OrderModel(0L, "0001", "Product 1", 1.00f, 1));
		orders.add(new OrderModel(1L, "0002", "Product 2", 2.00f, 2));
		orders.add(new OrderModel(2L, "0003", "Product 3", 3.00f, 3));
		orders.add(new OrderModel(3L, "0004", "Product 4", 4.00f, 4));
		orders.add(new OrderModel(4L, "0005", "Product 5", 5.00f, 5));
		
		model.addAttribute("title", "Order Form");
		model.addAttribute("orders", orders);
		
		// Navigate back to the Login view
		return "orders";
	}

}
