package com.gcu.repository;

import org.springframework.data.repository.CrudRepository;

import com.gcu.entity.UserEntity;


/* // This interface extends from CrudRepository, which is taken from Spring.
 * It provides CRUD methods which will save development time for this milestone.
 * @Param - Extends CrudRepository UserEntity, String
 * 
 */
public interface UsersRepository extends CrudRepository<UserEntity, Long>
{
	/*
	 * 
	 * Get the Album by ID
	 * @Param String id;
	 */
	UserEntity getUserById(String id);	
	
	UserEntity findByUsername(String id);	

}
