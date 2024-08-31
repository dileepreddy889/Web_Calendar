package webCalendarSpring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webCalendarSpring.model.Event;
import webCalendarSpring.repo.EventRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepo eventRepo;

    public void addEvent(Event event) {
        eventRepo.save(event);
    }

    public List<Event> getEvent() {
        return eventRepo.findAll();
    }

    public List<Event> findByDate(LocalDate date) {
        return eventRepo.findByDate(date);
    }

    public Optional<Event> findById(Long id) {
        return eventRepo.findById(id);
    }

    public Optional<Event> deleteById(Long id) {
        Optional<Event> event = eventRepo.findById(id);
        if (event.isPresent()) {
            eventRepo.deleteById(id);  // Delete event only if it exists
        }
        return event;
    }

    public List<Event> findEventsBetweenDates(LocalDate start, LocalDate end) {
        return eventRepo.findAllByDateBetween(start, end); // This method should exist in your repository
    }

}
