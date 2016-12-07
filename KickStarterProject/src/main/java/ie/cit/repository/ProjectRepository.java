package ie.cit.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ie.cit.entity.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {  //JpaRepository<Project, Long>{
	
	public Project findById(long id);
	
	public List<Project> findByStatusOrderByCreationDate(long status);
	
	public List<Project> findByNameContainsIgnoreCase(String name);
	public List<Project> findFirst3ByOrderByCreationDateDesc();
	public List<Project> findAllByOrderByCreationDate();
	
	public List<Project> findByName(String fullName); // SELECT * FROM projects WHERE name LIKE '%xxxxx%'

	public List<Project> findAll();
	
	public List<Project> findByStatus(long value);//Project 1= open 0 = closed
}