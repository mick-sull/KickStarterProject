package ie.cit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ie.cit.entity.Pledge;
import ie.cit.entity.User;
import ie.cit.repository.PledgeRepository;
import ie.cit.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootApplication
public class PledgeRepositoryTest {

	@Autowired
	private PledgeRepository pledgeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void findByIdTest(){
		Pledge pledge = pledgeRepository.findById(1);
		assertNotNull(pledge);
	}
	
	@Test
	public void findByUserTest(){
		User user = userRepository.findById(new Long(2));
		List<Pledge> pledges = pledgeRepository.findByUser(user);
		assertNotNull(pledges);
		assertTrue(pledges.size()>0);
	}
	
	@Test
	public void findAllTest(){
		List<Pledge> pledges = pledgeRepository.findAll();
		assertNotNull(pledges);
		assertTrue(pledges.size()>0);
	}
}
