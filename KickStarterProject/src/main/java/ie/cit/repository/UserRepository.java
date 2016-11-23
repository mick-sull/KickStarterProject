package ie.cit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ie.cit.entity.User;

public interface UserRepository extends CrudRepository{  //JpaRepository<User, Long> {
    User findByUsername(String username);
    
    // COLM EDITED  -  Find user(owner) by giving a project name//
    //@Query("SELECT u FROM User u JOIN u.project p WHERE p.name = :name")
	//public List<User> findByProjectName(@Param("name") String name);
}
