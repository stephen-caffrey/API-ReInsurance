package com.leaseplanis.data.impl;

import com.leaseplanis.data.EventRepository;
import com.leaseplanis.data.enums.Locations;
import com.leaseplanis.data.enums.Peril;
import com.leaseplanis.data.entities.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@Slf4j
public class EventRepositoryImpl implements EventRepository {

    private List<Event> events = new ArrayList<>();

    @Override
    public Event save(Event event) {
        event.setId(this.events.size()+1);
        saveAll(event);
        return event;
    }

    @Override
    public List<Event> findAll() {
        return this.events;
    }

    @Override
    public Event findById(int eventID) {
        return events.stream().filter(e->e.getId() == eventID).findAny().get();
    }

    protected void saveAll(Event... events) {
        this.events.addAll(Arrays.asList(events));
    }

    @PostConstruct
    private void init(){
        Event event1 = Event.builder().id(EventRepository.EVENT1_ID).peril(Peril.EARTHQUAKE).state(Locations.CALIFORNIA).loss(1000).build();
        Event event2 = Event.builder().id(EventRepository.EVENT2_ID).peril(Peril.FLOOD).state(Locations.LOUISIANA).loss(500).build();
        Event event3 = Event.builder().id(EventRepository.EVENT3_ID).peril(Peril.FLOOD).state(Locations.FLORIDA).loss(750).build();
        Event event4 = Event.builder().id(EventRepository.EVENT4_ID).peril(Peril.HURRICANE).state(Locations.FLORIDA).loss(2000).build();
        saveAll(event1,event2,event3,event4);
    }
}
