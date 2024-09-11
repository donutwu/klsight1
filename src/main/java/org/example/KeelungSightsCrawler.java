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
    //輸入關鍵字
    public Sight[] getItems(String keyword) {
        ArrayList sightList = new ArrayList();

        try {
            // 連接到網頁並抓取 HTML 文件
            Document doc = Jsoup.connect("https://www.travelking.com.tw/tourguide/taiwan/keelungcity/").timeout(10000).get();
            Elements h4Elements = doc.select("div.box > h4:containsOwn(" + keyword + "區)");
            Iterator var5 = h4Elements.iterator();

            while (true) {
                Element nextSibling;
                do {
                    do {
                        if (!var5.hasNext()) {
                            return (Sight[]) sightList.toArray(new Sight[0]);
                        }

                        Element h4 = (Element) var5.next();
                        // 獲取 <h4> 元素後面的第一個 <ul> 元素
                        nextSibling = h4.nextElementSibling();
                    } while (nextSibling == null);
                } while (!nextSibling.tagName().equals("ul"));

                Elements liElements = nextSibling.select("li a");
                Iterator var9 = liElements.iterator();

                while (var9.hasNext()) {
                    Element a = (Element) var9.next();
                    // 如果連接是相對路徑，轉換絕對路徑
                    String link = a.absUrl("href");
                    Document linkedDoc = Jsoup.connect(link).get();
                    Elements zonetag = linkedDoc.select("span[property=rdfs:label]");
                    String zone = zonetag.text();
                    Elements metaTags = linkedDoc.select("meta[itemprop]");
                    String nam = "";
                    //預設圖片
                    String img = "https://www.overseas.edu.tw/wp-content/uploads/2020/10/%E6%B5%B7%E6%B4%8B%E5%A4%A7%E5%AD%B8%E6%A0%A1%E9%96%80%E5%8F%A3%E3%80%8C%E6%B5%B7%E6%B4%8B%E6%84%8F%E8%B1%A1%E3%80%8D%E8%88%B9%E9%8C%A8%E6%99%AF%E8%A7%80-1024x629.png";
                    String des = "";
                    String addr = "";
                    Iterator var20 = metaTags.iterator();

                    while (var20.hasNext()) {
                        Element metaTag = (Element) var20.next();
                        String itemprop = metaTag.attr("itemprop");
                        String content = metaTag.attr("content");
                        //byte var25 = -1;
                        switch (itemprop) {
                            case "name":
                                // 處理 name 属性
                                nam = content;
                                //System.out.println("Name: " + content);
                                break;

                            case "image":
                                // 處理 address 属性
                                if(content.equals(""))
                                    img=img;
                                else img = content;
                                //System.out.println("image: " + content);
                                break;
                            case "description":
                                // 處理 address 属性
                                des = content;
                                //System.out.println("description: " + content);
                                break;
                            case "address":
                                // 處理 address 属性
                                addr = content;
                                //System.out.println("address: " + content);
                                break;
                            default:
                                // 處理其他属性
                                //System.out.println("Other itemprop: " + itemprop + ", Content: " + content);
                                break;
                        }


                    }
                    // 轉換絕對路徑
                    // File file = new File(img);
                    //String ablimg = file.getAbsolutePath();
                    Sight s1 = new Sight(nam, keyword + "區", zone, img, des, addr);
                    sightList.add(s1);
                }
            }
        } catch (IOException var26) {
            var26.printStackTrace();
            return (Sight[]) sightList.toArray(new Sight[0]);
        }
    }
}
