package com.universy.common.dynamo.mappercreators;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

public class DynamoDBLocalAccess extends DynamoDBAccess {

    private final String endpoint;
    private final String accessKey;
    private final String secretKey;

    public DynamoDBLocalAccess(String stage, String region, String endpoint, String accessKey, String secretKey) {
        super(stage, region);
        this.endpoint = endpoint;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    @Override
    protected AmazonDynamoDB getClient() {
        return getLocalClient();
    }

    private AmazonDynamoDB getLocalClient() {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(getEndpointConfiguration())
                .withCredentials(getCredentials())
                .build();
    }

    private AWSCredentialsProvider getCredentials() {
        return new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(accessKey, secretKey));
    }

    private AwsClientBuilder.EndpointConfiguration getEndpointConfiguration() {
        return new AwsClientBuilder
                .EndpointConfiguration(endpoint, getRegion());
    }
}
