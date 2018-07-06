package com.fubonlife.bio.exam.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.security.core.userdetails.User.UserBuilder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//import com.fubonlife.bio.mg.entity.mongo.User;

@Service
public class UserDetailsServiceImpl /*implements UserDetailsService*/ {
	
//	Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
//	
//	//@Autowired
//	//UserRepository userRepository;
//
//	@SuppressWarnings("unused")
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		
//		log.info("**********************************************");
//		log.info("username: "+username);
//	
//		/*Here we are using dummy data, you need to load user data from
//	     database or other third party application*/
//	    //User user = findUserbyUername(username);
//		
//		User user = new User();
//		user.setUserId("admin1");
//		user.setPassword("abc123");
//		user.setName("SHANG-CHUN, LIN");
//
//	    UserBuilder builder = null;
//	    if (user != null) {
//	      builder = org.springframework.security.core.userdetails.User.withUsername(username);
//	      builder.password(user.getPassword());
//	      
//	      String[] roles = {"ADMIN"};
//	      builder.roles(roles);
//	    } else {
//	      throw new UsernameNotFoundException("User not found.");
//	    }
//
//	    return builder.build();
//		
//		//UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(username);
//		
//		//org.springframework.security.core.userdetails.User
//		
//		//return builder.build();
//	}

}
