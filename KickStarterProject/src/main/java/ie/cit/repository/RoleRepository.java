package ie.cit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.cit.entity.Role;



public interface RoleRepository extends JpaRepository<Role, Long>{
}