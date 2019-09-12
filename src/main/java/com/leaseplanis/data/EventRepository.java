package com.leaseplanis.data;

import com.leaseplanis.data.entities.Event;

import java.util.List;
import java.util.Optional;

public interface EventRepository {

    int EVENT1_ID =1;
    int EVENT2_ID =2;
    int EVENT3_ID =3;
    int EVENT4_ID =4;

    Event save(Event event);

    List<Event> findAll();

    Event findById(int eventID);
}
