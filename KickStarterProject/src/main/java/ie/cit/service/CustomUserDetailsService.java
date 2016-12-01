package ie.cit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import ie.cit.config.SecurityUser;
import ie.cit.entity.User;
import ie.cit.repository.UserRepository;
import ie.cit.repository.RoleRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UserService userService;

	@Autowired
	public CustomUserDetailsService(UserService userService) {
		super();
		this.userService = userService;
	}
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("CustomUserDetailsService user: " + username);
		User user = userService.findUserByUsername(username);
		if (user == null) {
			System.out.println("CustomUserDetailsService user not found: ");
			throw new UsernameNotFoundException("UserName " + username + " not found");
			
		}
		else{
			System.out.println("CustomUserDetailsService  found user: " + username);
		}
		SecurityUser sUser= new SecurityUser(user);
		return sUser;
		
	}
}
	
	
	
	
	
	
	
	
	
	
	/*private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	
	
	
	@Autowired
    public CustomUserDetailsService(UserRepository userRepository,RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository=roleRepository;
    }
	
        
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepository.findByUsername(username);
		if(null == user){
			throw new UsernameNotFoundException("No user present with username: "+username);
		}else{
			List<String> userRoles=roleRepository.findRoleByUserName(username);
			return new CustomUserDetails(user,userRoles);
		}
	}*/
		
