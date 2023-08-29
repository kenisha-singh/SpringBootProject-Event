package demo.boot.service;

import java.util.List;


import org.springframework.http.ResponseEntity;

import demo.boot.model.Event;

public interface IEventService {
	
	ResponseEntity<List<Event>> getAllEvents();
	
	ResponseEntity<Event> getEventById(Long id);
	
	ResponseEntity<Event> createEvent(Event event);
	
	ResponseEntity<Event> updateEvent(Long id,Event event);
	
	ResponseEntity<Event> deleteEvent(Long id);

	

}
