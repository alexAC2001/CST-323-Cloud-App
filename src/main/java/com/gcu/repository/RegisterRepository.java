package com.gcu.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.gcu.entity.UserEntity;

// This extends from CrudRepository, which comes from SPRING, and it alreads has the CRUD methods!
// However, you must be mindful of the complexity of your application
public interface RegisterRepository extends CrudRepository<UserEntity, String> // Needs to be the SAME datatype as the ID to entity
{
	// Example of truly overriding a method from the CrudRepository and using our own customized SQL
	@Override
	@Query(value = "SELECT * FROM users")
	public List<UserEntity>findAll();
	
	UserEntity findByUsername(String username);	
	
}
