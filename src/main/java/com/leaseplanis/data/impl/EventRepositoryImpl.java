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
        events.add(event);
        return event;
    }

    @Override
    public Event findById(int eventID) {
        return events.stream().filter(e->e.getId() == eventID).findAny().get();
    }
}
