package ie.cit.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.cit.entity.Role;
import ie.cit.entity.User;
import ie.cit.repository.RoleRepository;

@Service
public class RoleService {
	public RoleRepository roleRepository;
	public EntityManager entityManager;
	@Autowired
	public RoleService(RoleRepository roleRepository,EntityManager entityManager) {
		this.roleRepository = roleRepository;
		this.entityManager=entityManager;
	}

	public List<Role> findAll() {
		return (List<Role>) roleRepository.findAll();
	}
	
	public boolean userHaveRole(User user,Role role){
//		CriteriaBuilder builder= entityManager.getCriteriaBuilder();
//		CriteriaQuery<Role> query=builder.createQuery(Role.class);
//		Root<Role> root=query.from(Role.class);
//		Predicate userRole = builder.equal(root.get(""), user.getId());
/*		System.out.println(user);
		System.out.println(role);
		if(user.getRoles()==null){
			return false;
		}
		if(user.getRoles().contains(role)){
			return true;
		}*/
		return false;
	}
}
