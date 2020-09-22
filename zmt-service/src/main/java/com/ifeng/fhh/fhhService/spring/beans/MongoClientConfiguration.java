package com.ifeng.fhh.fhhService.spring.beans;

import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoClients;
import com.mongodb.async.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-9-8
 */
@Configuration
public class MongoClientConfiguration {

    @Value("#{'${readPreference}'=='primary'?'${database.mongodb.driver.shard}':'${database.mongodb.driver.read.shard}'}")
    private String mongoURI;

    @Value("${database.mongodb.db}")
    private String databaseName;

    @Bean
    public MongoDatabase buildMongoDatabase(){
        MongoClient client = MongoClients.create(mongoURI);
        return client.getDatabase(databaseName);
    }
}
