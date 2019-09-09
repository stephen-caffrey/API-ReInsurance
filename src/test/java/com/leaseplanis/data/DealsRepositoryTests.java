package com.leaseplanis.data;

import com.leaseplanis.data.entities.Deal;
import com.leaseplanis.data.entities.Event;
import com.leaseplanis.data.enums.Locations;
import com.leaseplanis.data.enums.Peril;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DealsRepositoryTests {

    @Autowired
    private DealsRepository dealsRepository;

    @Autowired
    private EventRepository eventRepository;

    @Test
	public void testEvent1(){
        Event event = Event.builder().peril(Peril.EARTHQUAKE).state(Locations.CALIFORNIA).loss(1000).build();
        eventRepository.save(event);
		List<Deal> affectedDeals = this.dealsRepository.getAffected(event);
		assertTrue(affectedDeals.size() ==1);
        Optional<Deal> deal = affectedDeals.stream().filter(d -> d.getId() == dealsRepository.DEAL1_ID).findAny();
        assertTrue(deal.isPresent());
        assertTrue(deal.get().calculateLoss(event)==500);
	}

    @Test
    public void testEvent2(){
        Event event = Event.builder().peril(Peril.FLOOD).state(Locations.LOUISIANA).loss(500).build();
        List<Deal> affectedDeals = this.dealsRepository.getAffected(event);
        assertTrue(affectedDeals.size() ==1);
        Optional<Deal> deal = affectedDeals.stream().filter(d -> d.getId() == dealsRepository.DEAL3_ID).findAny();
        assertTrue(deal.isPresent());
        assertTrue(deal.get().calculateLoss(event)==250);
    }

    @Test
    public void testEvent3(){
        Event event = Event.builder().peril(Peril.FLOOD).state(Locations.FLORIDA).loss(750).build();
        List<Deal> affectedDeals = this.dealsRepository.getAffected(event);
        assertTrue(affectedDeals.size() ==1);

        Optional<Deal> deal = affectedDeals.stream().filter(d -> d.getId() == dealsRepository.DEAL3_ID).findAny();
        assertTrue(deal.isPresent());
        assertTrue(deal.get().calculateLoss(event)==250);
    }

    @Test
    public void testEvent4(){
        Event event = Event.builder().peril(Peril.HURRICANE).state(Locations.FLORIDA).loss(2000).build();
        List<Deal> affectedDeals = this.dealsRepository.getAffected(event);
        assertTrue(affectedDeals.size() ==2);

        Optional<Deal> deal2 = affectedDeals.stream().filter(d -> d.getId() == dealsRepository.DEAL2_ID).findAny();
        assertTrue(deal2.isPresent());
        assertTrue(deal2.get().calculateLoss(event)==1000);

        Optional<Deal> deal3 = affectedDeals.stream().filter(d -> d.getId() == dealsRepository.DEAL3_ID).findAny();
        assertTrue(deal3.isPresent());
        assertTrue(deal3.get().calculateLoss(event)==250);
    }
}
