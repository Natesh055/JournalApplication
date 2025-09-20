package com.edigest.journalApp;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@EnableTransactionManagement
@SpringBootApplication
public class JournalApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        System.setProperty("spring.data.mongodb.uri", dotenv.get("MONGODB_URI"));
        System.setProperty("spring.data.mongodb.database", dotenv.get("MONGO_DATABASE"));
        System.setProperty("api.key", dotenv.get("API_KEY"));
        SpringApplication.run(JournalApplication.class, args);
    }

    @Bean
    public PlatformTransactionManager kkj(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
