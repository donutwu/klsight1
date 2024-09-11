package org.example.service;

import org.example.Sight;
import org.example.repository.SightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SightServiceImpl implements SightService {
    @Autowired
    private SightRepository sightRepository;

    @Override
    public List<Sight> getSightsByZone(String zone) {
        return sightRepository.findAllByZone(zone + "區");
    }

    @Override
    public void printSights() {
        List<Sight> sights = sightRepository.findAll();
        for (Sight sight : sights) {
            System.out.println(sight.getSightName());
        }
    }
}
