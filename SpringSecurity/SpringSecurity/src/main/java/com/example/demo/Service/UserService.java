package com.example.demo.Service;

import java.util.List;

import com.example.demo.Entity.User;
import com.example.demo.Exception.UserException;

public interface UserService {

	public User registerUser(User user)throws UserException;
	public List<User> getAllUser()throws UserException;
	public User getUserById(Integer userId)throws UserException;
	
}
