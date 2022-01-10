package com.gcu.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.model.OrderModel;

public class OrdersBusinessService implements OrdersBusinessServiceInterface{
	
	// Class member variable
	@Autowired
	private OrdersBusinessServiceInterface service;
	
	@Override
	public void init()
	{
		System.out.println("In the OrdersBusinessService.init()");
	}

	@Override
	public void destroy()
	{
		System.out.println("In the OrdersBusinessService.destroy()");
	}
	
	@Override
	public void test()
	{
		System.out.println("Hello from the OrderBusinssService");
	}
	
	@Override
	public List<OrderModel> getOrders() {
		// Create some Orders and adding them to an array list
		List<OrderModel> orders = new ArrayList<OrderModel>();
		orders.add(new OrderModel(0L, "000000001", "Product 1", 1.00f, 1));
		orders.add(new OrderModel(1L, "000000002", "Product 2", 2.00f, 2));
		orders.add(new OrderModel(2L, "000000003", "Product 3", 3.00f, 3));
		orders.add(new OrderModel(3L, "000000004", "Product 4", 4.00f, 4));
		orders.add(new OrderModel(4L, "000000005", "Product 5", 5.00f, 5));
				
		// Return the list of orders
		return orders;
	}
	
}
