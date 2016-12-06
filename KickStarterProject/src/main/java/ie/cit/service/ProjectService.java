package ie.cit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ie.cit.entity.Project;
import ie.cit.repository.ProjectRepository;

@Service
@Transactional
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	public Project findById(long id){
		return projectRepository.findById(id);
	}
	
	public List<Project> findByName(String fullName){
		return projectRepository.findByName(fullName);
	}
	
	public Iterable<Project> findAll(){
		return projectRepository.findAll();
	}
	
	public Project save(Project proj){
		
		
		
		return projectRepository.save(proj);
	}
	
	public void delete(Project proj){
		projectRepository.delete(proj);
	}
	
	public void delete(Long id){
		projectRepository.delete(id);
	}
	
	public List<Project> findByStatus (long status){
		return projectRepository.findByStatus(status);
	}
	
}
