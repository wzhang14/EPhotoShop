package com.wei.EPhotoShop.config;

import com.wei.EPhotoShop.authetication.MyDBAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
// @EnableWebSecurity = @EnableWebMVCSECURITY + Extra features
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	MyDBAuthentiactionService myDBAuthenticationService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)throws Exception{
		
		//For User in database
		auth.userDetailsService(myDBAuthenticationService);
	}
	
	@Override
	protected void configure(HttpSecurity http)throws Exception{
		
		http.csrf().disable();
		
		// The pages requires login as EMPLOYEE or MANAGER.
		// If on login, it will redirect t /login page.
		http.authorizeRequests().antMatchers("/orderList","/order","/accountInfo")//
		    .access("hasAnyRole('ROLE_EMPLOYEE','ROLE_MANAGER')");
		
		// For MANAGER only
		http.authorizeRequests().antMatchers("/product").access("hasRole('ROLE-MANAGER')");
		
		// When the user has logged in as XX.
		// But access a page that requires role YY.
		// AccessDeniedException will throw.
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
		
		// Config for login form
		http.authorizeRequests().and().formLogin()//
		    // Submit URL of login page.
		    .loginProcessingUrl("/j_spring_security_check")//Submit URL
		    .loginpage("/login")//
		    .defaultSuccessUrl("/accountInfo")//
		    .failureUrl("/login?error=true")//
		    .usernameParameter("userName")//
		    .passwordParameter("password")
		    // Config for logout page
		    // Go to home page.
		    .and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
	}
}

