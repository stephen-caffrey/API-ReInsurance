package com.leaseplanis.api.controllers;

import com.leaseplanis.services.dao.Deal;
import com.leaseplanis.services.DealsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(path = APIVersion.VERSION)
public class DealController {

    DealsService dealsService;

    @Autowired
    DealController(DealsService dealsService){
        this.dealsService = dealsService;
    }

    @GetMapping("/deals/event/{id}")
    ResponseEntity<List<Deal>> get(@PathVariable int id) {
        return ResponseEntity.ok(dealsService.getAffected(id));
    }
}
