package com.gcu.data;

import java.util.List;
// Using Generics
// Contains CRUD methods
public interface DataAccessInterface<T>
{
	// What object oriented principle is being done here? 
	// Answer: Polymorphism
	public List<T> findAll();
	public T findById(int id);
	
	// This is not good since a boolean only returns 2 values, true or false
	public boolean create(T t);
	public boolean update(T t);
	public boolean delete(T t);
}
