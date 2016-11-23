package ie.cit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
public class Project {
	
	@Id
	@Column(name = "PROJ_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "PROJ_NAME", nullable = false,length = 50)
	private String name;
	
	@Column(name = "PROJ_DESC", nullable = false,length = 200)
	private String description;
	
	
	//private Image picture
	
	@Column(name = "GOAL_AMOUNT", precision = 2)
	private float goalAmount;
	
	
	//private Datetime timeLimit;
	
	@ManyToOne // (cascade = (All))
	private User owner;
	
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
	
	

	
	
}