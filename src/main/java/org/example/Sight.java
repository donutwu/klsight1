package org.example;

import java.io.Serializable;
import org.springframework.data.annotation.Id;

public class Sight implements Serializable {
   @Id
   private String id;
   private String sightName;
   private String zone;
   private String category;
   private String photoURL;
   private String description;
   private String address;

   public Sight(String sightName, String zone, String category, String photoURL, String description, String address) {
      this.setSightName(sightName);
      this.setZONE(zone);
      this.setCategory(category);
      this.setPhotoURL(photoURL);
      this.setdescription(description);
      this.setAddress(address);
   }

   public void setSightName(String sn) {
      this.sightName = sn;
   }

   public void setZONE(String ze) {
      this.zone = ze;
   }

   public void setCategory(String ce) {
      this.category = ce;
   }

   public void setPhotoURL(String url) {
      this.photoURL = url;
   }

   public void setAddress(String addr) {
      this.address = addr;
   }

   public void setdescription(String des) {
      this.description = des;
   }

   public String getSightName() {
      return this.sightName;
   }

   public String getZone() {
      return this.zone;
   }

   public String getCategory() {
      return this.category;
   }

   public String getPhotoURL() {
      return this.photoURL;
   }

   public String getAddress() {
      return this.address;
   }

   public String getDescription() {
      return this.description;
   }
}
