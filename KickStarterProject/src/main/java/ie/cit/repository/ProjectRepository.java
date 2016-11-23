package ie.cit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ie.cit.entity.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {  //JpaRepository<Project, Long>{
	
	// create method (save Project Object)
	
	
	// read method (Find all Projects)
	@Query("SELECT * FROM Project")
	public List<Project> read(@Param("name") String name);
	
	// read method (Find one Projects)
	@Query("SELECT * FROM Project")
	public List<Project> read(@Param("name") String name);
	
	// update method  (Save a Project Object)
	
	
	// delete method  ()
	
	
	public List<Project> findByName(String fullName); // SELECT * FROM projects WHERE name LIKE '%xxxxx%'

	public List<Project> findById(int id);
	
	@Query(value="Select * FROM projects WHERE id = :id", nativeQuery = true)
	public Project findByIdNative(@Param("id") int id);
	
}