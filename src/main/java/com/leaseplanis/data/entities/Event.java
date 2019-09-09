package com.leaseplanis.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leaseplanis.data.enums.Locations;
import com.leaseplanis.data.enums.Peril;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Builder
public class Event {
    @JsonIgnore
    private int id;
    private Peril peril;
    private Locations state;
    private int loss;
}
