package ie.cit.entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="pledges")
public class Pledge {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private double amount;
	
	@ManyToOne
    @JoinColumn(name = "user_id" )
	private User user;
	
	@ManyToOne
    @JoinColumn(name = "project_id" )
	private Project project;
	
	@Column(updatable=false, insertable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar pledgeDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
    public User getUser() {
        return user;
    }
	
	public void setUser(User user){
		this.user = user;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Calendar getPledgeDate() {
		return pledgeDate;
	}

	public void setPledgeDate(Calendar pledgeDate) {
		this.pledgeDate = pledgeDate;
	}
	
	public String getPledgeDateString(){
		String date = "";
		SimpleDateFormat formatter=new SimpleDateFormat("dd-MMM-yyyy");
		date = formatter.format(this.pledgeDate.getTime());
		return date;
	}
	
	public String getDateString(Calendar calander){
		String date = "";
		SimpleDateFormat formatter=new SimpleDateFormat("dd-MMM-yyyy");
		date = formatter.format(calander.getTime());
		return date;
	}
	
}
