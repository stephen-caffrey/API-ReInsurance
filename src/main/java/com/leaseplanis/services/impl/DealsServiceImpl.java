package com.leaseplanis.services.impl;

import com.leaseplanis.data.DealsRepository;
import com.leaseplanis.data.EventRepository;
import com.leaseplanis.data.entities.Event;
import com.leaseplanis.services.DealsService;
import com.leaseplanis.services.dao.Deal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DealsServiceImpl implements DealsService {

    private DealsRepository dealsRepository;
    private EventRepository eventRepository;

    @Autowired
    DealsServiceImpl(DealsRepository dealsRepository,
                     EventRepository eventRepository){
        this.dealsRepository = dealsRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Deal> getAffected(int eventID){
        Event event = eventRepository.findById(eventID);
        return dealsRepository.getAffected(event).stream().map(d -> Deal.builder().id(d.getId()).loss(d.calculateLoss(event)).build()).collect(Collectors.toList());
    }
}
