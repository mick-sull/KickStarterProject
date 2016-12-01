package ie.cit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ie.cit.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {  //JpaRepository<User, Long> {
    User findByUsername(String username);
    public User findByUsernameAndPassword(String username, String password);
    public User findById(long id);
    
    // create method (save Project Object)
	
	
 	// read method (Find all Projects)
 	
 	
 	// update method  (Save a Project Object)
 	
 	
 	// delete method  ()
    
    
    
    // COLM EDITED  -  Find user(owner) by giving a project name//
    //@Query("SELECT u FROM User u JOIN u.project p WHERE p.name = :name")
	//public List<User> findByProjectName(@Param("name") String name);
}
