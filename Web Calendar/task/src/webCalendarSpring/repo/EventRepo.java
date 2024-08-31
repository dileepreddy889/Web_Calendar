package webCalendarSpring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import webCalendarSpring.model.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventRepo extends JpaRepository<Event, Long> {
    // Ensure this method filters by date correctly
    List<Event> findByDate(LocalDate date);
    List<Event> findAllByDateBetween(LocalDate startDate, LocalDate endDate);
}
