package com.trax.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;


@Configuration
@EnableWebSecurity
@ComponentScan("com.trax")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    UserDetailsService customUserDetailsService;

	@Override
	protected void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(customAuthenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authenticationProvider(customAuthenticationProvider)

                .authorizeRequests()
                .antMatchers("/").authenticated()
                .antMatchers("/**").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/owner/**").hasRole("SUPER-USER")
                .antMatchers("/user/**").hasRole("OWNER")
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/index.html")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout");
	}

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**"); // #3
    }

}
