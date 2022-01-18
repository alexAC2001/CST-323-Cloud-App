package com.gcu.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.entity.UserEntity;
import com.gcu.model.UserModel;
import com.gcu.repository.UsersRepository;


/* This class implements the methods created from the UserDataAccessInterface
 * @Service
 * @Implements
 * @Param - UserEntity
 */
@Service
public class UserDataService implements UserDataAccessInterface<UserEntity> 
{
	
	/*
	 * 
	 * @Autowire reigsterRepository
	 */
	/*@Autowired
	private RegisterRepository registerRepository;*/
	
	@Autowired
	private UsersRepository usersRepository;

	/*  Non-default constructor for constructor injection
	 * @Param - registerRepository
	 * @Return - registerRepsoitory
	 * 
	 */
	/*public UserDataService(RegisterRepository registerRepository)
	{
		this.registerRepository = registerRepository;
	}*/

	public UserDataService(UsersRepository usersRepository)
	{
		this.usersRepository = usersRepository;
	}


	/* Find all the Registered Users in the database.
	 * Loop through the List<UserEntity> 
	 * @Return List of Users
	 * 
	 */
	@Override
	public List<UserEntity> findAll()
	{
		List<UserEntity> users = new ArrayList<UserEntity>();
	
		Iterable<UserEntity> usersIterable = usersRepository.findAll();
		users = new ArrayList<UserEntity>();
		usersIterable.forEach(users::add);

		// If the list is empty
		if(users.size() <= 0)
		{
			System.out.println("");
		}
		
		// return the list
		return users;		
	}
	
	/*
	 * 
	 * This method finds the album ID 
	 * @Param - String ID of the album entity
	 * @Return Id from the album.
	 */
	public UserEntity findById(String id)
	{				
		if (id.equals("0"))
		{
			System.out.println("");
		}	
		
		return usersRepository.getUserById(id);
	}
	
	/* Create the Register Profile
	 * @Param - UserModel user
	 * @Return newCred which is a created profile. 
	 * 
	 */
	@Override
	public UserEntity create(UserModel user)
	{		
		UserEntity newCred = null;
		newCred = new UserEntity(user.getId(), user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword());		
		newCred = this.usersRepository.save(newCred);

		if(newCred == null)
		{
			System.out.println("");
		}
		
		return newCred;
	}
	
	/* Login with list and UserModel 
	 * ITERATE Through the list. and compare
	 * @Param - UserModel user
	 * @Return - Return false if count isnt 0
	 */
	@SuppressWarnings("unlikely-arg-type")
	@Override
	public boolean login(UserModel user) 
	{
		int count = 0;
		List<UserEntity> users = new ArrayList<UserEntity>();

		users = findAll();
		
		for(int i = 0; i <users.size(); i++) 
		{		
			if(users.get(i).getUsername().equals(user.getUsername()) && users.get(i).getPassword().equals(user.getPassword())); 
			{
				System.out.println(users.get(i).getUsername() + " Is Apparantly Equal To " + user.getUsername());
				count++;
			}			
		}
		if(count == 1)				
			return true;
		
		if(!users.contains(user))
		{
			System.out.print("");
		}

		System.out.println(count);
		System.out.print(user.getUsername());
		System.out.print(user.getPassword());		
		return false;
	}


	/* This calls the findByUsername method from the RegisterRepository class
     * Will return a UserEntity when username is found in the database
	 * @param - String username
	 * @Return found username, 
	 * @throw new runtime exception
	 *
	 */
	@Override
	public UserEntity findByUsername(String username) 
	{		
		try
		{
			return usersRepository.findByUsername(username);
		}
		catch(Exception e)
		{
			System.out.println("");
		}
		return usersRepository.findByUsername(username);
	}
	
	@Override
	public UserEntity update(UserModel user) 
	{		
		// Make a new album entity
		UserEntity userEntity = new UserEntity(user.getId(), user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword());
		// Making a new album entity, saving the album entity, then return it at the end of the method.
		userEntity = this.usersRepository.save(userEntity);

		return userEntity;
	}	
	public void DeleteUser(Long id) 
	{	
		usersRepository.deleteById(id);
		Optional<UserEntity> user;
		user = usersRepository.findById(id);				
	}

}
