package ie.cit.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name="projects")
public class Project {
	
	@Id
	@Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "name", nullable = false,length = 50)
	private String name;
	
	@Column(name = "description", nullable = false,length = 200)
	private String description;
	
	
	//private Image picture
	
	@Column(name = "goalAmount", precision = 2)
	private float goalAmount;
	
	
	//private Datetime timeLimit;
	@ManyToOne
	@JoinColumn(name = "owner_id" )
	private User owner;
	
	
	//List of contributers
	//List<User> contributers;
	
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
	List<Pledge> pledges; 
	
	
	
	public Project(){}	

	public Project(long id, String name, String description, float goalAmount) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.goalAmount = goalAmount;
		
		
		
		// chage 18.18
	}
	
	
	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this);
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public float getGoalAmount() {
		return goalAmount;
	}


	public void setGoalAmount(float goalAmount) {
		this.goalAmount = goalAmount;
	}
	
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<Pledge> getPledges() {
		return pledges;
	}

	public void setPledges(List<Pledge> pledges) {
		this.pledges = pledges;
	}

	public double getPledged(){
		double balance = 0;
		for(Pledge pledge: pledges){
			balance+=pledge.getAmount();
		}
		return balance;
	}
	
	public double getBalance(){
		double pledged = goalAmount - getBalance();
		return pledged;
	}
	
}