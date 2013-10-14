package com.trax.init;

import javax.sql.DataSource;

import com.trax.services.user.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;


@Configuration
@EnableWebSecurity
@ComponentScan("com.trax.services")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

    @Autowired
    UserDetailsService customUserDetailsService;

	@Override
	protected void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.userDetailsService(customUserDetailsService)
			.authorizeRequests()
			.antMatchers("/sec/moderation.html").hasRole("SUPER-USER")
			.antMatchers("/admin/**").hasRole("ADMIN")
			.and()
			.formLogin()
			.loginPage("/user-login.html")
			.defaultSuccessUrl("/success-login.html")
			.failureUrl("/error-login.html")
			.permitAll()
			.and()
			.logout()
			.logoutSuccessUrl("/index.html");
	}

}
