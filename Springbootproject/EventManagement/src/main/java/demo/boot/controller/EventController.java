package demo.boot.controller;


import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import demo.boot.model.Event;
import demo.boot.service.EventService;
import demo.boot.service.KafkaConsumer;


@RestController

public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }
    @Autowired
    KafkaConsumer kafkaConsumer;

    @GetMapping("/")
    @CircuitBreaker(name="EventService", fallbackMethod="defaultMsg")
    public HttpEntity<List<Event>> getAllEvents() {	
    	return eventService.getAllEvents();
       
    }

    @GetMapping("/{id}")
    public HttpEntity<Event> getEventById(@PathVariable Long id) {   	    	
        return eventService.getEventById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
    	return eventService.createEvent(event);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event upEvent) {
       return eventService.updateEvent(id, upEvent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
       return eventService.deleteEvent(id);
    }

      
	public ResponseEntity<String> defaultMsg(Exception e) {
        return ResponseEntity.ok("Hmmmmm .....Can't reach this event page.....Try Again !!!") ;

    }
    
}