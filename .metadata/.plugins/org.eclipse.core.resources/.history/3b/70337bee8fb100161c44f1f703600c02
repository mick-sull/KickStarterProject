package ie.cit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.cit.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
