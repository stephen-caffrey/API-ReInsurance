package com.leaseplanis.api.controllers;

import com.leaseplanis.data.EventRepository;
import com.leaseplanis.data.entities.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(path = APIVersion.VERSION)
public class EventController {

    private final EventRepository repository;

    @Autowired
    EventController(EventRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/event")
    ResponseEntity<Event> newEvent(@RequestBody Event newEvent) {
        repository.save(newEvent);
        URI location = URI.create(APIVersion.VERSION+"/event/"+newEvent.getId());
        return ResponseEntity.created(location).body(newEvent);
    }

    @GetMapping("/events")
    ResponseEntity<List<Event>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }
}
