package com.trax.services.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.trax.dao.user.UserDAO;
import com.trax.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly=true)
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserDAO userDAO;

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		com.trax.models.User domainUser = userDAO.getUser(username);
		
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		return new org.springframework.security.core.userdetails.User(
				domainUser.getUsername(),
				domainUser.getPassword(), 
				enabled, 
				accountNonExpired, 
				credentialsNonExpired, 
				accountNonLocked,
				getAuthorities(domainUser.getRoles())
		);
	}


	
	public Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roles) {
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(roles));
		return authList;
	}
	
	public List<String> getRoles(Set<Role> roles) {

		List<String> allRoles = new ArrayList<String>();

        for(Role role: roles){
            if(role.getCode().equals("SUPER-USER")){
                allRoles.add("ROLE_SUPER-USER");
                allRoles.add("ROLE_ADMINISTRATOR");
            }

            else if(role.getCode().equals("ADMINISTRATOR")){
                allRoles.add("ROLE_ADMINISTRATOR");
            }
        }

		return allRoles;
	}
	
	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}

}
