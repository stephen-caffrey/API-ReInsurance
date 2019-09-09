package com.leaseplanis.data.entities;

import com.leaseplanis.data.enums.Locations;
import com.leaseplanis.data.enums.Peril;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.EnumSet;
import java.util.Optional;

@Slf4j
@Builder
@Data
public class Deal {
    private int id;
    private int retention;
    private int limit;
    private EnumSet<Peril> perils;
    private EnumSet<Locations> states;
    private boolean covered;

    public Optional<Deal> cover(Event event) {
        if(perils.contains(event.getPeril()) && states.contains(event.getState())){
            return Optional.of(this);
        }else{
            return Optional.empty();
        }
    }

    public int calculateLoss(Event event){

        //If the total loss for the event is less than the retention then the customer will pay and the loss for the deal will be 0..
        if(event.getLoss() <= getRetention()){
            return 0;
        }

        // Otherwise, deduct the retention
        int adjustedLoss = event.getLoss() - getRetention();

        if(adjustedLoss >= limit){
            return limit;
        }else{
            return adjustedLoss;
        }
    }
}
