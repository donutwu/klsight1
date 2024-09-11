package org.example.controller;

import java.util.List;

import org.example.Sight;
import org.example.service.SightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SightController {
    @Autowired
    private SightService sightService;

    @CrossOrigin(
            origins = {"*"}
    )
    @GetMapping({"/printSights"})
    public void printSights() {
        sightService.printSights();

    }

    @CrossOrigin(
            origins = {"*"}
    )
    @GetMapping({"/SightAPI"})
    public List<Sight> getSightsByZone(@RequestParam String zone) {
        System.out.println(sightService.getSightsByZone(zone));
        return sightService.getSightsByZone(zone);
    }
}
