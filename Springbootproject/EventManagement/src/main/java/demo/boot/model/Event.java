package demo.boot.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Event extends RepresentationModel<Event> {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String eventName;
	private String speaker;
	
	@ManyToOne
	@JoinColumn(name="locationId")
	@Cascade(CascadeType.ALL)
	private Location location;
	
	

	public Event(long id, String eventName, String speaker, Location location) {
		super();
		this.id = id;
		this.eventName = eventName;
		this.speaker = speaker;
		this.location = location;
	}

	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getSpeaker() {
		return speaker;
	}

	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	
}
