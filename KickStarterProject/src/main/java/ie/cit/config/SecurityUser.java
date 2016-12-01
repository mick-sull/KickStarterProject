package ie.cit.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ie.cit.entity.Role;
import ie.cit.entity.User;
import ie.cit.service.CustomUserDetailsService;
import ie.cit.service.UserService;

	public class SecurityUser extends User implements UserDetails {

		private static final long serialVersionUID = 1L;
		
		@Autowired
		private CustomUserDetailsService customUserDetailsService;
		
		@Autowired
		private UserService detailsService;
		
		@Autowired
		 public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		  //auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
		  auth.userDetailsService(customUserDetailsService);
		 }

		public SecurityUser(User user) {
			System.out.println("SecurityUser  found user: " + user.getUsername());
			this.setId(user.getId());
			this.setUsername(user.getUsername());
			this.setPassword(user.getPassword());
			//this.setRoles(user.getRoles());
		}

/*		public Collection<? extends GrantedAuthority> getAuthorities() {
			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(0);
			Set<Role> userRoles = this.getRoles();
			if (userRoles != null) {
				for (Role role : userRoles) {
					SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
					authorities.add(authority);
				}
			}
			System.out.println("Roles: "+authorities.size());
			authorities.stream().forEach(System.out::println);
			System.out.println("==========");
			return authorities;
		}*/

		public String getPassword() {
			return super.getPassword();
		}

		public String getUsername() {
			return super.getUsername();
		}

		public boolean isAccountNonExpired() {
			return true;
		}

		public boolean isAccountNonLocked() {
			return true;
		}

		public boolean isCredentialsNonExpired() {
			return true;
		}

		public boolean isEnabled() {
			return true;
		}
		
/*		@Bean
		public PasswordEncoder passwordEncoder() {
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			return encoder;
		}*/

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			// TODO Auto-generated method stub
			return null;
		}


	
}
