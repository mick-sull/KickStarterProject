package ie.cit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	
/*	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="artist_movements",
			joinColumns={@JoinColumn(name="artist_id", referencedColumnName="id")},
			inverseJoinColumns={@JoinColumn(name="movement_id", referencedColumnName="id")})
	public List<Movement> movements;
	*/
	
	
	
	
}