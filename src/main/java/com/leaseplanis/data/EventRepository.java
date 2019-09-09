package com.leaseplanis.data;

import com.leaseplanis.data.entities.Event;

import java.util.Optional;

public interface EventRepository {
    Event save(Event event);

    Event findById(int eventID);
}
