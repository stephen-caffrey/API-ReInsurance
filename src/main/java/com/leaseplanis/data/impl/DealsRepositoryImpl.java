package com.leaseplanis.data.impl;

import com.leaseplanis.data.DealsRepository;
import com.leaseplanis.data.enums.Locations;
import com.leaseplanis.data.enums.Peril;
import com.leaseplanis.data.entities.Deal;
import com.leaseplanis.data.entities.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DealsRepositoryImpl implements DealsRepository {

    private List<Deal> deals;

    @Override
    public void save(Deal... newDeals) {
        if (deals == null){
            deals = Arrays.asList(newDeals);
        }else{
            deals.addAll(Arrays.asList(newDeals));
        }
    }

    @Override
    public List<Deal> getAffected(Event event) {
        return this.deals.stream().filter( d -> d.cover(event).isPresent()).collect(Collectors.toList());
    }

    @PostConstruct
    private void init(){
        Deal deal1 = Deal.builder()
                .id(DEAL1_ID)
                .perils(EnumSet.of(Peril.EARTHQUAKE))
                .states(EnumSet.of(Locations.CALIFORNIA))
                .retention(100)
                .limit(500).build();

        Deal deal2 = Deal.builder()
                .id(DEAL2_ID)
                .perils(EnumSet.of(Peril.HURRICANE))
                .states(EnumSet.of(Locations.FLORIDA))
                .retention(1000)
                .limit(3000).build();

        Deal deal3 = Deal.builder()
                .id(DEAL3_ID)
                .perils(EnumSet.of(Peril.HURRICANE, Peril.FLOOD))
                .states(EnumSet.of(Locations.FLORIDA, Locations.LOUISIANA))
                .retention(250)
                .limit(250).build();

        save(deal1, deal2, deal3);
    }
}
