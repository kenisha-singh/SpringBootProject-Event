package demo.boot.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Location {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long locationId;
	private String place;
	private Date date;
	public Location() {
		super();
	}
	public Location(long locationId, String place, Date date) {
		super();
		this.locationId = locationId;
		this.place = place;
		this.date = date;
	}
	public long getLocationId() {
		return locationId;
	}
	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
		
}