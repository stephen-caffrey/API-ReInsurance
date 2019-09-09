package com.leaseplanis.services;

import com.leaseplanis.services.dao.DealDao;

import java.util.List;

public interface DealsService {
    List<DealDao> getAffected(int eventID);
}
