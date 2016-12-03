package ie.cit;

import static org.junit.Assert.*;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ie.cit.entity.Project;
import ie.cit.entity.User;
import ie.cit.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootApplication
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void findByUsername(){
		User user = userRepository.findByUsername("smithy12");
		assertNotNull(user);
	}
	
	@Test
    public void findByUsernameAndPassword(){
		User user = userRepository.findByUsernameAndPassword("smithy12", "password");
		assertNotNull(user);
    }
    
	@Test
    public void findById(){
		User user = userRepository.findById(1);
		assertNotNull(user);
    }
	
	@Test
	@Transactional
	public void save(){
		
		//Project proj = projectRepository.save(testProj);
		User user = userRepository.findById(1);
		user.setUsername("TestName");
		User user2 = userRepository.save(user);
		assertNotNull(user2);
	}
	
	@Test
	@Transactional
	public void delete(){
		int size = userRepository.findAll().size() - 1;
		userRepository.delete(new Long(2));
		List<User> users = userRepository.findAll();
		assertEquals(size, users.size());
		User user = userRepository.findById(2);
		assertNull(user);
	}
    
}
