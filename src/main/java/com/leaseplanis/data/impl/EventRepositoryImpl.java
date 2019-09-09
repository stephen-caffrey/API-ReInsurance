package com.leaseplanis.data.impl;

import com.leaseplanis.data.EventRepository;
import com.leaseplanis.data.enums.Locations;
import com.leaseplanis.data.enums.Peril;
import com.leaseplanis.data.entities.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EventRepositoryImpl implements EventRepository {

    private List<Event> events;

    public EventRepositoryImpl save(Event... events) {
        if (this.events == null){
            this.events = Arrays.asList(events);
        }else{
            this.events.addAll(Arrays.asList(events));
        }
        return this;
    }

    @Override
    public Optional<Event> findById(int eventID) {
        return events.stream().filter(e->e.getId() == eventID).findAny();
    }

    @PostConstruct
    private void init(){
        Event event1 = Event.builder().id(EventRepository.EVENT1_ID).peril(Peril.EARTHQUAKE).state(Locations.CALIFORNIA).loss(1000).build();
        Event event2 = Event.builder().id(EventRepository.EVENT2_ID).peril(Peril.FLOOD).state(Locations.LOUISIANA).loss(500).build();
        Event event3 = Event.builder().id(EventRepository.EVENT3_ID).peril(Peril.FLOOD).state(Locations.FLORIDA).loss(750).build();
        Event event4 = Event.builder().id(EventRepository.EVENT4_ID).peril(Peril.HURRICANE).state(Locations.FLORIDA).loss(2000).build();
        save(event1,event2,event3,event4);
    }
}
