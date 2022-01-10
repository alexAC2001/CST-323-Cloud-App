package com.gcu.business;

import java.util.ArrayList;
import java.util.List;

import com.gcu.model.OrderModel;

public class AnotherOrdersBusinessService implements OrdersBusinessServiceInterface {

	@Override
	public void test() {
		System.out.println("Hello from the AnotherOrdersBusinessService");		
	}

	@Override
	public List<OrderModel> getOrders() {
		// Create some Orders and adding them to an array list
			List<OrderModel> orders = new ArrayList<OrderModel>();
			orders.add(new OrderModel(0L, "000000001", "Product 32", 1.45f, 1));
			orders.add(new OrderModel(1L, "000000002", "Product 64", 2.52f, 2));
			orders.add(new OrderModel(2L, "000000003", "Product 75", 3.74f, 3));
			orders.add(new OrderModel(3L, "000000004", "Product 44", 4.75f, 4));
			orders.add(new OrderModel(4L, "000000005", "Product 23", 5.05f, 5));
				
		// Return the list of orders
		return orders;
	}

	@Override
	public void init()
	{
		System.out.println("In the AnotherOrdersBusinessService.init()");
	}

	@Override
	public void destroy()
	{
		System.out.println("In the AnotherOrdersBusinessService.destroy()");
	}
}
