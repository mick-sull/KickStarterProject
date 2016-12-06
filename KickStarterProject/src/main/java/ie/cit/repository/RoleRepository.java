package ie.cit.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ie.cit.entity.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

/*	@Query("select a.role from Role a, User b where b.username=?1 and a.id=b.id")
    public java.util.List<String> findRoleByUserName(String username);*/
	public Role findById(long id);
	
	//@Query("select a.role from UserRole a, User b where b.username=?1 and a.id=b.id")
	public Set<Role> findRoleByUserId(long id);
}