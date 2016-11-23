package ie.cit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.cit.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{

	// Change 17.55
}