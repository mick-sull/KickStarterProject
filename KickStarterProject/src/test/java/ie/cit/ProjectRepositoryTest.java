package ie.cit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ie.cit.entity.Project;
import ie.cit.repository.ProjectRepository;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootApplication
public class ProjectRepositoryTest {

	@Autowired
	private ProjectRepository projectRepository;
	
	
	//@InjectMocks
	//private Project testProj = new Project();
	
	@Before
    public void setupMock() {
       MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testFindById(){
		
		Project proj = projectRepository.findById(1);
		assertNotNull(proj);
		//verify(projectRepository).findById(1);
	}
	
	@Test
	public void testFindByName(){
		List<Project> projects = projectRepository.findByName("smithy12");
		assertNotNull(projects);
		
	}
	
	@Test
	@Transactional
	public void save(){
		
		//Project proj = projectRepository.save(testProj);
		Project proj = projectRepository.findById(1);
		proj.setName("TestName");
		Project proj2 = projectRepository.save(proj);
		assertNotNull(proj2);
	}
	
	@Test
	@Transactional
	public void delete(){
		projectRepository.delete(new Long(2));
		List<Project> projects = projectRepository.findAll();
		assertEquals(3, projects.size());
		Project proj = projectRepository.findById(2);
		assertNull(proj);
	}
	
}
