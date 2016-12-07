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
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="projects")
public class Project implements Comparable<Project>{
	
	@Id
	@Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Size(min=2, max=50)
	@Column(name = "name", nullable = false, length = 50)
	private String name;
	
	@Size(min=20, max=2000)
	@Column(name = "description", nullable = false, length = 2000)
	private String description;
	
	@Max(1000000)
	@Min(100)
	@Column(name = "goalAmount", precision = 2)
	private float goalAmount;
	
	@ManyToOne
	@JoinColumn(name = "owner_id" )
	private User owner;
	
	
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
	List<Pledge> pledges; 
	
	@Future
	@Temporal(TemporalType.DATE)
	@DateTimeFormat (pattern="yyyy-MM-dd")
	private Calendar deadLine;
	
	@Temporal(TemporalType.TIMESTAMP)
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private Calendar creationDate;
	
	private String imagePath;
	
	@Column(name = "status")
	private long status;
	
	
	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

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
	
	public int getDaysToGo(){
		Calendar today = Calendar.getInstance();
		Long milis = this.getDeadLine().getTime().getTime() - today.getTime().getTime();
		int days = (int)(milis/(1000*60*60*24));
		return days;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	@Override
	public int compareTo(Project otherProj) {
	    return otherProj.getCreationDate().compareTo(this.getCreationDate());
	}
	
}