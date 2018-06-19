package com.learning.aws;

import com.learning.aws.SNS.SNSTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AwsdemoApplication implements CommandLineRunner {
    @Autowired
    private SNSTopic snsTopic;

    public static void main(String[] args) {
        SpringApplication.run(AwsdemoApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("-------------------START----------------------");
        String result = snsTopic.createTopic();
        snsTopic.subscribeTopic();
        snsTopic.publishToTopic(result);
        System.out.println("-------------------Ended----------------------");
    }
}