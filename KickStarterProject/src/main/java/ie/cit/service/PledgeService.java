package ie.cit.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ie.cit.entity.Pledge;
import ie.cit.entity.Project;
import ie.cit.entity.User;
import ie.cit.repository.PledgeRepository;

@Service
@Transactional
public class PledgeService {
	
	@Autowired
	private PledgeRepository pledgeRepository;

	
	public void add(Pledge pledge, User user, Project project) {
		pledge.setUser(user);
		System.out.println("pledge add: " + user.getUsername());
		pledge.setProject(project);
		System.out.println("pledge project: " + project.getName());
		
		Calendar today = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(dateFormat.format(today.getTime()));
		
		pledge.setPledgeDate(today);
		
		//pledge.setPledgeDate(today.getTime());
		pledgeRepository.save(pledge);
/*		Pledge sPledge = pledgeRepository.save(pledge);
		System.out.println("sPledge " + sPledge.getAmount());
		System.out.println("sPledge " + sPledge.getPledgeDate());
		System.out.println("sPledge " + sPledge.getId());*/

	}
	
	
	public void deleteById(Long id)
	{
		pledgeRepository.delete(id);
	}
	
	public double getTotalAmountOfPledges(User user){
		List<Pledge> pledges = pledgeRepository.findByUser(user);
		double totalPledges = 0;
		if(pledges.isEmpty()){
			return 0;
		}
		else{
			for (int i = 0; i<pledges.size(); i++){
				totalPledges += pledges.get(i).getAmount();
			}
		}
		return totalPledges;
		
	}


	public Iterable<Pledge> findFirst5ByOrderByPledgeDateDesc() {
		
		return pledgeRepository.findFirst5ByOrderByPledgeDateDesc();
	}


	public void delete(long id) {
		pledgeRepository.delete(id);
		
	}
}
