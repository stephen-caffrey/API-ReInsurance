package com.leaseplanis.data;

import com.leaseplanis.data.entities.Event;

import java.util.Optional;

public interface EventRepository {

    public int EVENT1_ID =1;
    public int EVENT2_ID =2;
    public int EVENT3_ID =3;
    public int EVENT4_ID =4;

    Optional<Event> findById(int eventID);
}
