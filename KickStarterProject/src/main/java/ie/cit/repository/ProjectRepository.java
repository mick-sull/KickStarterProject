package ie.cit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ie.cit.entity.Pledge;
import ie.cit.entity.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {  //JpaRepository<Project, Long>{
	
	// create method (save Project Object)
	
	
	// read method (Find all Projects)
	//@Query("SELECT * FROM Project")
	//public List<Project> readAll();
	
	// read method (Find one Projects)
	public Project findById(long id);
	
	//@Query(value="Select * FROM projects WHERE id = :id", nativeQuery = true)
	//public Project findByIdNative(@Param("id") int id);
	
	public List<Project> findFirst3ByOrderByCreationDateDesc();
	public List<Project> findAllByOrderByCreationDate();
	
	// update method  (Save a Project Object)
	
	
	// delete method  ()
	
	public Project findById(Long id);
	public List<Project> findByName(String fullName); // SELECT * FROM projects WHERE name LIKE '%xxxxx%'


	public List<Project> findAll();
	
/*	@Query(value="SELECT * FROM projects WHERE status =?1", nativeQuery=true)
	public List<Project> findBystatus(long value);//Project 1= open 0 = closed
*/	//@Query(value="SELECT * FROM projects WHERE status =?1", nativeQuery=true)
	public List<Project> findByStatus(long value);//Project 1= open 0 = closed
}