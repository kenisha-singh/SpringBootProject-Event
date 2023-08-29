package demo.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import demo.boot.model.Event;

@Service
public class EventService implements IEventService{
	@Autowired
	 RestTemplate restTemplate;
	

	 private String getBaseUrl() {
		 
	        return "http://EventService"; 
	    }

	 
	@Override
	public ResponseEntity<List<Event>> getAllEvents() {
		ParameterizedTypeReference<List<Event>> responseType = new ParameterizedTypeReference<List<Event>>() {};
		ResponseEntity<List<Event>> responseEntity = restTemplate.exchange(getBaseUrl(), HttpMethod.GET, null, responseType);
        return responseEntity;
	}



	@Override
	public ResponseEntity<Event> getEventById(Long id) {
		 return restTemplate.getForEntity(getBaseUrl() + "/" + id, Event.class);
	}

	@Override
	public ResponseEntity<Event> createEvent(Event event) {
		return restTemplate.postForEntity(getBaseUrl(), event,Event.class);

	}

	@Override
	public ResponseEntity<Event> updateEvent(Long id, Event event) {
		return restTemplate.exchange(getBaseUrl() + "/" + id, HttpMethod.PUT, new HttpEntity<>(event), Event.class);

	}

	@Override
	public ResponseEntity<Event> deleteEvent(Long id) {
		restTemplate.delete(getBaseUrl() + "/" + id);
        return ResponseEntity.ok().build();
	}
	
	
	public String defaultMsg(Exception e) {
		return "\t\t\t\t\t\t\t Hmmmmm .....Can't reach this event page \n \\t\\t\\t\\t\\t\\t Try Again !!!";

    }
	
	


}
