package org.example.service;

import org.example.Sight;
import org.example.Traceable;
import org.example.ZoneNotFoundException;
import org.example.repository.SightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SightServiceImpl implements SightService {
    @Autowired
    private SightRepository sightRepository;
    @Traceable
    @Override
    public List<Sight> getSightsByZone(String zone) {
        List<Sight> sights = sightRepository.findAllByZone(zone + "區");
        if (sights.isEmpty()) {
            throw new ZoneNotFoundException("The zone " + zone + " does not exist");
        }
        return sights;
    }
    @Traceable
    @Override
    public void printSights() {
        List<Sight> sights = sightRepository.findAll();
        for (Sight sight : sights) {
            System.out.println(sight.getSightName());
        }
    }
}
