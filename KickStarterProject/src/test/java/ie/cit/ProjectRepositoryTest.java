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
	public void saveTest(){
		
		//Project proj = projectRepository.save(testProj);
		Project proj = projectRepository.findById(1);
		proj.setName("TestName");
		Project proj2 = projectRepository.save(proj);
		assertNotNull(proj2);
	}
	
	@Test
	@Transactional
	public void deleteTest(){
		projectRepository.delete(new Long(2));
		List<Project> projects = projectRepository.findAll();
		assertEquals(7, projects.size());
		Project proj = projectRepository.findById(2);
		assertNull(proj);
	}
	
	public void findByStatusOrderByCreationDateTest(long status){
		List<Project> projects = projectRepository.findByStatusOrderByCreationDate(0);
		assertNotNull(projects);
	}
	
	public void findByNameContainsIgnoreCaseTest(){
		List<Project> projects = projectRepository.findByNameContainsIgnoreCase("smithy12");
		assertNotNull(projects);
		List<Project> projects2 = projectRepository.findByNameContainsIgnoreCase("smITHY12");
		assertNotNull(projects2);
	}
	
	public void findFirst3ByOrderByCreationDateDescTest(){
		List<Project> projects = projectRepository.findFirst3ByOrderByCreationDateDesc();
		assertNotNull(projects);
		assertEquals(projects.size(), 3);
	}
	public void findAllByOrderByCreationDateTest(){
		List<Project> projects = projectRepository.findAll();
		assertNotNull(projects);
	}

	public void findAllTest(){
		List<Project> projects = projectRepository.findAll();
		assertNotNull(projects);
	}
	
	public void findByStatusTest(){
		List<Project> projects = projectRepository.findByStatus(0);
		assertNotNull(projects);
	}
	
}
