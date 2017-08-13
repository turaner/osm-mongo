package com.javaoptimum.osmmongo.importer;

import com.javaoptimum.osmmongo.importer.service.OsmImporter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableAutoConfiguration
@Configuration
@EnableAsync
@ComponentScan(basePackages = "com.javaoptimum.osmmongo")
@EnableMongoRepositories(basePackages = "com.javaoptimum.osmmongo.repo")
public class Application {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);

        OsmImporter osmImporter = ctx.getBean(OsmImporter.class);
        osmImporter.process();

        try {
            // Wait till asynchronous persist operations are finished.
            // TODO join thread pool
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ctx.close();
    }

}