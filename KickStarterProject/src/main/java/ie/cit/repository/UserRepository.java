package ie.cit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ie.cit.entity.Pledge;
import ie.cit.entity.Project;
import ie.cit.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {  //JpaRepository<User, Long> {
    
	public User findByUsername(String username);
    public User findByUsernameAndPassword(String username, String password);
    public User findById(long id);

    public List<User> findAll();

    
    // create method (save Project Object)
	
	
 	// read method (Find all Projects)
 	
 	
 	// update method  (Save a Project Object)
 	
 	
 	// delete method  ()
    
    
    //public List<Pledge> findByUserid(long id);
    
    // COLM EDITED  -  Find user(owner) by giving a project name//
    //@Query("SELECT a FROM Artist a JOIN a.movements m WHERE m.name = :name")
    //@Query("SELECT amount FROM pledges pl, projects pr JOIN pl.project p WHERE pl.user_id = :user_id")
	//public List<Project> getProjectsPledged(@Param("user_id") String user_id);
    
}
