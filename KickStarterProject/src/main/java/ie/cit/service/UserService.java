package ie.cit.service;


import ie.cit.entity.User;

public interface UserService {
    
    public void save(User user);


    public User findByUsername(String username);
}