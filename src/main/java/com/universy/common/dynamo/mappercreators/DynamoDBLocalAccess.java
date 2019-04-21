package com.universy.common.dynamo.mappercreators;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.universy.common.dynamo.environment.DynamoEnvironment;

public class DynamoDBLocalAccess extends DynamoDBAccess {


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
                new BasicAWSCredentials(DynamoEnvironment.getAccessKeyId(), DynamoEnvironment.getSecretAccessKey()));
    }

    private AwsClientBuilder.EndpointConfiguration getEndpointConfiguration() {
        return new AwsClientBuilder
                .EndpointConfiguration(DynamoEnvironment.getDynamoEndpoint(), DynamoEnvironment.getRegion());
    }
}
