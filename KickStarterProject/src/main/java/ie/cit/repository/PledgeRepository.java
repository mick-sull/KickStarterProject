package ie.cit.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ie.cit.entity.Pledge;
import ie.cit.entity.User;

public interface PledgeRepository extends CrudRepository<Pledge, Long> {

	public Pledge findById(int id);
	public List<Pledge> findByUser(User user);
	public List<Pledge> findFirst5ByOrderByIdDesc();
	public List<Pledge> findAll();
	

	
}
