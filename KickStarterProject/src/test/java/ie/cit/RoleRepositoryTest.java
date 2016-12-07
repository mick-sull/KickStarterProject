package ie.cit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ie.cit.entity.Project;
import ie.cit.entity.Role;
import ie.cit.repository.RoleRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootApplication
public class RoleRepositoryTest {

	@Autowired
	private RoleRepository roleRepository;
	
	@Test
	@Transactional
	public void save(){
		
		Role role = roleRepository.findById(1);
		role.setRole("TestName");
		Role role2 = roleRepository.save(role);
		assertNotNull(role2);
	}
	
	@Test
	@Transactional
	public void delete(){
		int size = roleRepository.findAll().size()-1;
		roleRepository.delete(new Long(2));
		List<Role> roles = roleRepository.findAll();
		assertEquals(size, roles.size());
		Role role = roleRepository.findById(2);
		assertNull(role);
	}
}
