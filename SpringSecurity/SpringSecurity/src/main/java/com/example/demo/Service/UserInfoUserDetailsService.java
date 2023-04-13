package com.example.demo.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.DAO.UserDAO;
import com.example.demo.Entity.User;
import com.example.demo.SpringSecurityConfig.UserInfoUserDetails;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Optional<User> user = userDAO.findByUsername(username);
		if(user.isEmpty())throw new UsernameNotFoundException("User Does Not Exist With Username "+username);
		
		UserInfoUserDetails userInfoUserDetails = new UserInfoUserDetails(user.get());
		
		return userInfoUserDetails;
	}

}
