package ie.cit.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
public class Project {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	private String description;
	//private Image picture
	private float goalAmount;
	//private Datetime timeLimit;
	
	public Project(){}	
	
	public Project(long id, String name, String description, float goalAmount) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.goalAmount = goalAmount;
		// chage 17.44
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