package org.example.repository;

import java.util.List;

import org.example.Sight;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SightRepository extends MongoRepository<Sight, String> {
   List<Sight> findAllByZone(String zone);
}
