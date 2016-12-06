package ie.cit.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
    @NotNull
    @Size(min=2, max=30, message = "Username size should be in the range [2...30]")
    private String username;
    
    @NotNull
    @Size(min=1, max=50)
    private String password;
    
    // Set up when registering?? 
    // when making a pledge add up all the existing user pledge
    // amounts
    @Range(min=1, max=1000000)
    private float creditLimit; 
    
  /*  @NotNull
	@Column(name = "role")
	private String role;
	
 //   private String passwordConfirm;
	
    public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}*/

/*	@ManyToMany
    @JoinTable(name = "user_role", 
    	joinColumns = @JoinColumn(name = "user_id"), 
    	inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;*/
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Pledge> pledges;
	
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	private List<Project> projects;

/*	public User(User user) {
		this.id = user.id;
        this.username = user.username;  
        this.password = user.password;  
	}*/


	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

    public float getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(float creditLimit) {
		this.creditLimit = creditLimit;
	}

	/*    @Transient
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
*/
/*    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    */
    
    public List<Pledge> getPledges() {
        return pledges;
    }
    
    public void setPledges(List<Pledge> pledges){
    	this.pledges = pledges;
    }
    
    public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", creditLimit=" + creditLimit
				+ "]";
	}

/*	public String getUserName() {
		// TODO Auto-generated method stub
		return username;
	}*/
	
	
    
}