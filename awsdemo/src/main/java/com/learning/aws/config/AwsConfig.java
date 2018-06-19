package com.learning.aws.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AwsConfig {

    public AmazonSNSClient SNSConfig() {
        AWSCredentials credentials = new ProfileCredentialsProvider("saml").getCredentials();
        AmazonSNSClient service = new AmazonSNSClient(credentials);
        System.out.println("Connected");
        return service;
    }

}
