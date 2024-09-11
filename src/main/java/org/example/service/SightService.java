package org.example.service;
import org.example.Sight;

import java.util.List;
public interface SightService {
    List<Sight>getSightsByZone(String zone);
    void printSights();
}
