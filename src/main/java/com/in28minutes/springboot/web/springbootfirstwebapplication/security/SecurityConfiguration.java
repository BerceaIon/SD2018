package com.in28minutes.springboot.web.springbootfirstwebapplication.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.in28minutes.springboot.web.springbootfirstwebapplication.model.User;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter  {
	com.in28minutes.springboot.web.springbootfirstwebapplication.model.User user = new User("USER", "Bercea", "Ionut","dummy",
			 1500);
	
	@Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance()).withUser(user.getFirstName()).password(user.getPassword())
                .roles("USER", "ADMIN");
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/login", "/h2-console/**").permitAll()
				.antMatchers("/", "/*todo*/**").access("hasRole('USER')").and()
				.formLogin();
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}
}
