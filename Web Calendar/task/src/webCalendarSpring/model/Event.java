package webCalendarSpring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    private String event;
    @NotNull
    private LocalDate date;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull String getEvent() {
        return event;
    }

    public void setEvent(@NotNull String event) {
        this.event = event;
    }

    public @NotNull LocalDate getDate() {
        return date;
    }

    public void setDate(@NotNull LocalDate date) {
        this.date = date;
    }

    public Event(LocalDate date, String event, Long id) {
        this.date = date;
        this.event = event;
        this.id = id;
    }

    public Event(){

    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", event='" + event + '\'' +
                ", date=" + date +
                '}';
    }
}
