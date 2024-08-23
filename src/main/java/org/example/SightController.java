package org.example;

import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SightController {
   @Autowired
   private SightRepository sightRepository;

   @CrossOrigin(
      origins = {"*"}
   )
   @GetMapping({"/printSights"})
   public void printSights() {
      List<Sight> sights = this.sightRepository.findAll();
      Iterator var2 = sights.iterator();

      while(var2.hasNext()) {
         Sight sight = (Sight)var2.next();
         System.out.println(sight.getSightName());
      }

   }

   @CrossOrigin(
      origins = {"*"}
   )
   @GetMapping({"/SightAPI"})
   public List<Sight> getSightsByZone(@RequestParam String zone) {
      System.out.println(this.sightRepository.findAllByZone(zone));
      return this.sightRepository.findAllByZone(zone + "區");
   }
}
