package ie.cit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ie.cit.entity.Project;

public interface ProjectRepository extends CrudRepository{  //JpaRepository<Project, Long>{
	
	// create method (save Project Object)
	
	
	// read method (Find all Projects)
	
	
	// update method  (Save a Project Object)
	
	
	// delete method  ()
	
	
	//@Query("SELECT p FROM Artist a JOIN a.movements m WHERE m.name = :name")
	//public List<Project> findByMovementsName(@Param("name") String name);
}