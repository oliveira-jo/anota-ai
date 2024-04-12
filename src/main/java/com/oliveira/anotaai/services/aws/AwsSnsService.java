package com.oliveira.anotaai.services.aws;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;

@Service
public class AwsSnsService {

  AmazonSNS scsClient;
  Topic catalogTopic;

  public AwsSnsService(AmazonSNS scsClient, @Qualifier("catalogEventsTopic") Topic catalogTopic) {
    this.catalogTopic = catalogTopic;
    this.scsClient = scsClient;
  }

  public void publish(MessageDTO message) {
    this.scsClient.publish(catalogTopic.getTopicArn(), message.message());
  }
}
