package ie.cit.service;

import java.sql.Timestamp;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ie.cit.entity.Pledge;
import ie.cit.entity.Project;
import ie.cit.entity.User;
import ie.cit.repository.PledgeRepository;

@Service
@Transactional
public class PledgeService {
	private PledgeRepository pledgeRepository;

	
	public void add(Pledge pledge, User user, Project project) {
		pledge.setUser(user);
		System.out.println("pledge add: " + user.getUsername());
		pledge.setProject(project);
		System.out.println("pledge project: " + project.getName());
		
		Calendar today = Calendar.getInstance();
		
		pledge.setPledgeDate(today);
		
		//pledge.setPledgeDate(today.getTime());
		pledgeRepository.save(pledge);

	}
	
	
	public void deleteById(Long id)
	{
		pledgeRepository.delete(id);
	}
}
