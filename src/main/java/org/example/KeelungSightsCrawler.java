package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
@Component
public class KeelungSightsCrawler {
   public Sight[] getItems(String keyword) {
      ArrayList sightList = new ArrayList();

      try {
         Document doc = Jsoup.connect("https://www.travelking.com.tw/tourguide/taiwan/keelungcity/").get();
         Elements h4Elements = doc.select("div.box > h4:containsOwn(" + keyword + "區)");
         Iterator var5 = h4Elements.iterator();

         while(true) {
            Element nextSibling;
            do {
               do {
                  if (!var5.hasNext()) {
                     return (Sight[])sightList.toArray(new Sight[0]);
                  }

                  Element h4 = (Element)var5.next();
                  nextSibling = h4.nextElementSibling();
               } while(nextSibling == null);
            } while(!nextSibling.tagName().equals("ul"));

            Elements liElements = nextSibling.select("li a");
            Iterator var9 = liElements.iterator();

            while(var9.hasNext()) {
               Element a = (Element)var9.next();
               String link = a.absUrl("href");
               Document linkedDoc = Jsoup.connect(link).get();
               Elements zonetag = linkedDoc.select("span[property=rdfs:label]");
               String zone = zonetag.text();
               Elements metaTags = linkedDoc.select("meta[itemprop]");
               String nam = "";
               String img = "";
               String des = "";
               String addr = "";
               Iterator var20 = metaTags.iterator();

               while(var20.hasNext()) {
                  Element metaTag = (Element)var20.next();
                  String itemprop = metaTag.attr("itemprop");
                  String content = metaTag.attr("content");
                  byte var25 = -1;
                  switch(itemprop.hashCode()) {
                  case -1724546052:
                     if (itemprop.equals("description")) {
                        var25 = 2;
                     }
                     break;
                  case -1147692044:
                     if (itemprop.equals("address")) {
                        var25 = 3;
                     }
                     break;
                  case 3373707:
                     if (itemprop.equals("name")) {
                        var25 = 0;
                     }
                     break;
                  case 100313435:
                     if (itemprop.equals("image")) {
                        var25 = 1;
                     }
                  }

                  switch(var25) {
                  case 0:
                     nam = content;
                     break;
                  case 1:
                     img = content;
                     break;
                  case 2:
                     des = content;
                     break;
                  case 3:
                     addr = content;
                  }
               }
               // 转换为绝对路径
               File file = new File(img);
               String ablimg = file.getAbsolutePath();
               Sight s1 = new Sight(nam, keyword + "區", zone, ablimg, des, addr);
               sightList.add(s1);
            }
         }
      } catch (IOException var26) {
         var26.printStackTrace();
         return (Sight[])sightList.toArray(new Sight[0]);
      }
   }
}
