package com.oliveira.anotaai.config.aws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.Topic;

@Configuration
public class AwsSnsConfig {
  @Value("${aws.region}")
  private String region;
  @Value("${aws.accessKeyId}")
  private String accessKeyId;
  @Value("${aws.secretKey}")
  private String sercreKey;
  @Value("${aws.sns.topic.catalog.arn}")
  private String catalogTopicArn;

  @Bean
  public AmazonSNS amazonSNSBuilder() {
    BasicAWSCredentials credentials = new BasicAWSCredentials(accessKeyId, sercreKey);

    return AmazonSNSClientBuilder
        .standard()
        .withCredentials(new AWSStaticCredentialsProvider(credentials))
        .withRegion(region)
        .build();
  }

  @Bean(name = "catalogEventsTopic")
  public Topic snsCatalogTopicBuilder() {
    return new Topic().withTopicArn(catalogTopicArn);
  }

}
