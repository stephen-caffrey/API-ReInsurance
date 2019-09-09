package com.leaseplanis.services.dao;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Builder
public class Deal {
    public int id;
    public int loss;
}
