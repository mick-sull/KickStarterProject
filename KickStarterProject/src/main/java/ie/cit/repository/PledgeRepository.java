package ie.cit.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ie.cit.entity.Pledge;
import ie.cit.entity.User;

@Repository
public interface PledgeRepository extends CrudRepository<Pledge, Long> {

	public Pledge findById(long id);
	public List<Pledge> findByUser(User user);
	public List<Pledge> findFirst5ByOrderByPledgeDateDesc();
	public List<Pledge> findAll();
	
	
	
	
}
