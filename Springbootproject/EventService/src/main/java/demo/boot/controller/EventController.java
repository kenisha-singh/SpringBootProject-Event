package demo.boot.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import demo.boot.dao.EventDAO;
import demo.boot.exception.EventNotFoundException;
import demo.boot.model.Event;
import demo.boot.service.KafKaProducer;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController

public class EventController {

	private final EventDAO eventDAO;

	@Autowired
	KafKaProducer producer;

	@Autowired
	public EventController(EventDAO eventDAO) {
		this.eventDAO = eventDAO;
	}

	@GetMapping("/")
	public ResponseEntity<List<Event>> getAllEvents() {
		List<Event> events = eventDAO.findAllEvents();
		producer.sendMsgToTopic(events);
		for (Event event : events) {
			event.add(linkTo(methodOn(EventController.class).getAllEvents()).withSelfRel());
			event.add(linkTo(EventController.class).slash("id").withRel("Event By Id"));
			event.add(linkTo(methodOn(EventController.class).createEvent(event)).withRel("Create Event"));
		}

		return ResponseEntity.ok(events);
	}

	@GetMapping("/{id}")
	public EntityModel<Optional<Event>> getEventById(@PathVariable Long id) {
		Optional<Event> eventById = eventDAO.findEventById(id);
		Link getAllLink = linkTo(EventController.class).slash(id).withSelfRel();
		Link getAllLink2 = linkTo(methodOn(EventController.class).getAllEvents()).withRel("Get All Event");
		Link getAllLink3 = linkTo(EventController.class).slash("/").withRel("Create Event");

		if (eventById.isEmpty()) {
			throw new EventNotFoundException("Event with Id " + id + " is not Found");
		}
		return EntityModel.of(eventById, getAllLink, getAllLink2, getAllLink3);
	}

	@PostMapping("/")
	@Transactional
	public ResponseEntity<Event> createEvent(@Valid @RequestBody Event event) {
		Event createdTask = eventDAO.createEvent(event);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Event> updateEvent(@PathVariable Long id, @Valid @RequestBody Event upEvent) {
		Optional<Event> existingEventOptional = eventDAO.findEventById(id);

		if (!existingEventOptional.isEmpty()) {
			Event event = existingEventOptional.get();
			event.setEventName(upEvent.getEventName());
			event.setSpeaker(upEvent.getSpeaker());

			Event updated = eventDAO.updateEvent(event);
			return ResponseEntity.ok(updated);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEvent(@Valid @PathVariable Long id) {
		Optional<Event> event = eventDAO.findEventById(id);

		if (event.isPresent()) {
			eventDAO.deleteEventById(event.get());
			return ResponseEntity.noContent().build();
		} else {
			throw new EventNotFoundException("Event  With  id: " + id + " Not Found So Cannot be deleted..");
		}
	}
}
