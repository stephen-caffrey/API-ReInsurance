package com.leaseplanis.data;

import com.leaseplanis.data.entities.Deal;
import com.leaseplanis.data.entities.Event;

import java.util.List;

public interface DealsRepository {

    public int DEAL1_ID =1;
    public int DEAL2_ID =2;
    public int DEAL3_ID =3;

    List<Deal> getAffected(Event event);

    void save(Deal... newDeals);
}
