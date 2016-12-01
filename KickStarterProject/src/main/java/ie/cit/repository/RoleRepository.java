package ie.cit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ie.cit.entity.Role;



public interface RoleRepository extends JpaRepository<Role, Long>{

/*	@Query("select a.role from Role a, User b where b.username=?1 and a.id=b.id")
    public java.util.List<String> findRoleByUserName(String username);*/
}