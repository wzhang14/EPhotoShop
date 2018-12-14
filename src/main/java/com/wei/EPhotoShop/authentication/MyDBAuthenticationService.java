package com.wei.EPhotoShop.authentication;

import java.util.ArrayList;
import java.util.List;

import com.wei.EPhotoShop.dao.AccountDAO;
import com.wei.EPhotoShop.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGranedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetaisService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyDBAuthenticationService implements UserDetailsService{
	
	@Autowired
	private AccountDAO accountDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException{
		Account account = accountDAO.findAccount(username);
		System.out.println("Account= " + account);
		
		if(account == null) {
			throw new UsernameNotFoundException("User "//
					+ username + " was not found in the database");
		}
		
		//EMPLOYEE,MANAGER...
		String role = account.getUserRole();
		
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		
		//ROLE_EMPLOYEE, ROLE_MANAGER
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+ role);
		
		grantList.add(authority);
		
		boolean enable = account.isActive();
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		
		UserDetails userDetails = (UserDetails) new User(account.getUserName(),//
				account.getPassword(), enabled, accountNonExpired,//
				credentialsNonExpired, accountNonLocked, grantList);
		
		return userDetails;
	}

}
