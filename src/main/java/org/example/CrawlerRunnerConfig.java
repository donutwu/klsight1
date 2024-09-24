package org.example;

import org.example.repository.SightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CrawlerRunnerConfig {
    @Autowired
    private KeelungSightsCrawler crawler;
    @Autowired
    private SightRepository sightRepository;

    @Bean
    public ApplicationRunner applicationRunner() {
        return (args) -> {
           //存取地點
            String[] defaultZones = new String[]{"七堵", "中山", "中正", "仁愛", "安樂", "信義", "暖暖"};
            String[] var3 = defaultZones;
            int var4 = defaultZones.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                String zone = var3[var5];
                Sight[] sights = this.crawler.getItems(zone);
                Sight[] var8 = sights;
                int var9 = sights.length;

                for (int var10 = 0; var10 < var9; ++var10) {
                    Sight sight = var8[var10];
                    this.sightRepository.save(sight);
                }
            }

            System.out.println("Default crawling and saving to MongoDB completed.");
        };
    }
}
