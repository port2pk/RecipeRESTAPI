package com.recipe.security;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	 @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 System.out.println("executed loadUserByUsername");
	        return new User("user", "password",
	                new ArrayList<>());
	    }

}
