package com.amicolon.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.inMemoryAuthentication()
				.withUser("admin").password(passwordEncoder().encode("test")).roles("ADMIN")
				.and()
				.withUser("home").password(passwordEncoder().encode("test2")).roles("USER")
				.and()
				.passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{

		http
				.authorizeRequests()
				.antMatchers("/category/{id}/delete").hasRole("ADMIN")
				.antMatchers("/category/{id}/update").hasRole("ADMIN")
				.antMatchers("/category/new").hasRole("ADMIN")
				.antMatchers("/forms/category").hasRole("ADMIN")
				.antMatchers("/**").hasAnyRole("ADMIN","USER")
				.antMatchers("/showLoginPage").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
					.loginPage("/showLoginPage")
					.loginProcessingUrl("/login")
					.defaultSuccessUrl("/panel", true)
					.failureUrl("/showLoginPage")
					.permitAll()
				.and()
					.logout().permitAll();

	}

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

}
