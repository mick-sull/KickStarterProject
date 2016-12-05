package ie.cit.entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name="projects")
public class Project {
	
	@Id
	@Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "name", nullable = false,length = 50)
	private String name;
	
	@Column(name = "description", nullable = false,length = 200)
	private String description;
	
	@Column(name = "goalAmount", precision = 2)
	private float goalAmount;
	
	@ManyToOne
	@JoinColumn(name = "owner_id" )
	private User owner;
	
	
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
	List<Pledge> pledges; 
	
	@Temporal(TemporalType.DATE)
	private Calendar deadLine;
	
	@Temporal(TemporalType.TIMESTAMP)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Calendar creationDate;
	
	private String imagePath;
	
	
	public Project(){}
	
	public Project(String name, String description, float goalAmount){
		this.name = name;
		this.description = description;
		this.goalAmount = goalAmount;
	}

	public Project(long id, String name, String description, float goalAmount) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.goalAmount = goalAmount;
		
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
		double pledged = 0;
		for(Pledge pledge: pledges){
			pledged+=pledge.getAmount();
		}
		return pledged;
	}
	
	public double getBalance(){
		double balance = goalAmount - getBalance();
		return balance;
	}

	public Calendar getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(Calendar deadLine) {
		this.deadLine = deadLine;
	}

	public Calendar getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}
	
	public String getCreationDateString(){
		String date = "";
		SimpleDateFormat formatter=new SimpleDateFormat("dd-MMM-yyyy");
		date = formatter.format(this.creationDate.getTime());
		return date;
	}
	
	public String getDateString(Calendar calander){
		String date = "";
		SimpleDateFormat formatter=new SimpleDateFormat("dd-MMM-yyyy");
		date = formatter.format(calander.getTime());
		return date;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	
	
}