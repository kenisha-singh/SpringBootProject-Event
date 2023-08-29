package demo.boot.dao;

import org.springframework.stereotype.Repository;


import org.springframework.transaction.annotation.Transactional;

import demo.boot.model.Event;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

import java.util.Optional;

@Repository
@Transactional
public class EventDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Event> findAllEvents() {
        return entityManager.createQuery("FROM Event ", Event.class).getResultList();
    }

    public Optional<Event> findEventById(Long id) {
        return Optional.ofNullable(entityManager.find(Event.class, id));
    }

    public Event createEvent(Event event) {
        entityManager.persist(event);
        return event;
    }

    public Event updateEvent(Event event) {
        return entityManager.merge(event);
    }
  
    public void deleteEventById(Event event) {
        entityManager.remove(event);
    }
}
