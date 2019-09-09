package com.leaseplanis.services;

import com.leaseplanis.services.dao.Deal;

import java.util.List;

public interface DealsService {
    List<Deal> getAffected(int eventID);
}
