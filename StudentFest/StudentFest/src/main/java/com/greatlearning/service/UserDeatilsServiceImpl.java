/**
 * 
 */
package com.greatlearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.greatlearning.entity.User;
import com.greatlearning.repository.UserRepository;
import com.greatlearning.security.MyUserDetails;

/**
 * @author Aravind
 *
 */
public class UserDeatilsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub

		User user = userRepository.getUserbyUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException(username + "! user not found.");
		}

		return new MyUserDetails(user);
	}

}
