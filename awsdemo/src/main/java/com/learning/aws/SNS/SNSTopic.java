package com.learning.aws.SNS;

import com.amazonaws.services.sns.model.*;
import com.learning.aws.config.AwsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SNSTopic {
    @Autowired
    private AwsConfig config;
    @Autowired
    private AmazonSNSHandler amazonSNSHandler;

    public String createTopic() {
        CreateTopicRequest topicRequest = new CreateTopicRequest("MyTopic");
        CreateTopicResult topicResult = config.SNSConfig().createTopic(topicRequest);
        String topicArn = topicResult.getTopicArn();
        System.out.println("The Topic details are :" + topicResult.toString());
        return topicArn;
    }

    public void subscribeTopic(){

        SubscribeRequest subscribeRequest = new SubscribeRequest("arn:aws:sns:us-east-1:608014515287:MyTopic", "email", "krishnaram492@gmail.com");
        System.out.println("Sending subscription request to subscribers : mail");
        config.SNSConfig().subscribe(subscribeRequest);
        System.out.println("SubscribeRequest - " + config.SNSConfig().getCachedResponseMetadata(subscribeRequest));


    }

    public void publishToTopic(String topicArn) {
        String msg = "Hi Ram, This is test message from AWS SNS";
        PublishRequest publishRequest = new PublishRequest(topicArn, msg);
        PublishResult publishResult = config.SNSConfig().publish(publishRequest);
        System.out.println("MessageId - " + publishResult.getMessageId());
    }


}
